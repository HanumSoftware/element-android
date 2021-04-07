/*
 * Copyright 2019 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.app.features.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.facebook.appevents.AppEventsLogger
import im.vector.app.API
import im.vector.app.BuildConfig
import im.vector.app.ContextInjector
import im.vector.app.LogEventBody
import im.vector.app.R
import im.vector.app.core.di.DefaultSharedPreferences
import im.vector.app.core.extensions.observeK
import im.vector.app.core.extensions.replaceChildFragment
import im.vector.app.core.platform.VectorBaseFragment
import im.vector.app.databinding.FragmentHomeDrawerBinding
import im.vector.app.features.grouplist.GroupListFragment
import im.vector.app.features.settings.VectorPreferences
import im.vector.app.features.settings.VectorSettingsActivity
import im.vector.app.features.usercode.UserCodeActivity
import im.vector.app.features.workers.signout.SignOutUiWorker
import org.matrix.android.sdk.api.session.Session
import org.matrix.android.sdk.api.util.toMatrixItem
import javax.inject.Inject

class HomeDrawerFragment @Inject constructor(
        private val session: Session,
        private val vectorPreferences: VectorPreferences,
        private val avatarRenderer: AvatarRenderer
) : VectorBaseFragment<FragmentHomeDrawerBinding>() {

    private lateinit var sharedActionViewModel: HomeSharedActionViewModel

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeDrawerBinding {
        return FragmentHomeDrawerBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(R.drawable.logo_animation).into(view.findViewById(R.id.animation_view))

        sharedActionViewModel = activityViewModelProvider.get(HomeSharedActionViewModel::class.java)

        if (savedInstanceState == null) {
            replaceChildFragment(R.id.homeDrawerGroupListContainer, GroupListFragment::class.java)
        }
        session.getUserLive(session.myUserId).observeK(viewLifecycleOwner) { optionalUser ->
            val user = optionalUser?.getOrNull()
            if (user != null) {
                avatarRenderer.render(user.toMatrixItem(), views.homeDrawerHeaderAvatarView)
                views.homeDrawerUsernameView.text = user.displayName
                views.homeDrawerUserIdView.text = user.userId
            }
        }
        // Profile
        views.homeDrawerHeader.debouncedClicks {
            sharedActionViewModel.post(HomeActivitySharedAction.CloseDrawer)
            navigator.openSettings(requireActivity(), directAccess = VectorSettingsActivity.EXTRA_DIRECT_ACCESS_GENERAL)
        }
        // Settings
        views.homeDrawerHeaderSettingsView.debouncedClicks {
            sharedActionViewModel.post(HomeActivitySharedAction.CloseDrawer)
            navigator.openSettings(requireActivity())
        }
        // About us
        views.aboutUs.debouncedClicks {
            sharedActionViewModel.post(HomeActivitySharedAction.CloseDrawer)
            navigator.openAboutUs(requireActivity())
        }

        views.donationsBtn.debouncedClicks {
            sharedActionViewModel.post(HomeActivitySharedAction.CloseDrawer)
            navigator.openDonation(requireActivity())
        }

        views.leaderboardBtn.debouncedClicks {
            sharedActionViewModel.post(HomeActivitySharedAction.CloseDrawer)
            navigator.openLeaderboard(requireActivity())
        }

        views.youtubeBtn.debouncedClicks {
            sharedActionViewModel.post(HomeActivitySharedAction.CloseDrawer)

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCvWvxk-42WtpSBshdTCVJig"))
            startActivity(browserIntent)
        }

        views.instagramBtn.debouncedClicks {
            sharedActionViewModel.post(HomeActivitySharedAction.CloseDrawer)

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/gideonsaar"))
            startActivity(browserIntent)
        }

        views.facebookBtn.debouncedClicks {
            sharedActionViewModel.post(HomeActivitySharedAction.CloseDrawer)

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/GideonSaarIL"))
            startActivity(browserIntent)
        }

        views.twitterBtn.debouncedClicks {
            sharedActionViewModel.post(HomeActivitySharedAction.CloseDrawer)

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/gidonsaar?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor"))
            startActivity(browserIntent)
        }

        views.telegramBtn.debouncedClicks {
            sharedActionViewModel.post(HomeActivitySharedAction.CloseDrawer)

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/gideonsaar"))
            startActivity(browserIntent)
        }

        views.volunteerBtn.debouncedClicks {
            sharedActionViewModel.post(HomeActivitySharedAction.CloseDrawer)
            navigator.openVolunteering(requireActivity())
        }

        // Sign out
        views.homeDrawerHeaderSignoutView.debouncedClicks {
            sharedActionViewModel.post(HomeActivitySharedAction.CloseDrawer)
            SignOutUiWorker(requireActivity()).perform()
        }

        views.supportBtn.debouncedClicks {
            sharedActionViewModel.post(HomeActivitySharedAction.CloseDrawer)
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/972538997688"))
            startActivity(browserIntent)
        }

        views.homeDrawerQRCodeButton.debouncedClicks {
            UserCodeActivity.newIntent(requireContext(), sharedActionViewModel.session.myUserId).let {
                val options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                                requireActivity(),
                                views.homeDrawerHeaderAvatarView,
                                ViewCompat.getTransitionName(views.homeDrawerHeaderAvatarView) ?: ""
                        )
                startActivity(it, options.toBundle())
            }
        }

        views.homeDrawerInviteFriendButton.debouncedClicks {
            AppEventsLogger.newLogger(context).logEvent("ShareAPP")

            val prefs = ContextInjector.applicationContext?.let { DefaultSharedPreferences.getInstance(it) }

            if (prefs?.getBoolean("shareapp_first", true) == true) {

                with(prefs.edit()) {
                    putBoolean("shareapp_first", false)
                    apply()
                }


                API.performLogEvent(LogEventBody("shareapp_first", "${session.myUserId}", 2000))
            } else {
                API.performLogEvent(LogEventBody("shareapp", "${session.myUserId}", 1000))
            }

            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Gidonline")
                val shareMessage = "Gidonline\nהאפליקציה הרשמית של מפלגת תקווה חדשה\nתצטרפו לקהילה שלנו!\n\nלהורדה:\nApp Store:\nhttps://apps.apple.com/us/app/gidonline/id1552672966\n\nPlay Store:\nhttps://play.google.com/store/apps/details?id=com.elekt.gidonline.android"
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
                //e.toString();
            }
        }

        // Debug menu
        views.homeDrawerHeaderDebugView.isVisible = BuildConfig.DEBUG && vectorPreferences.developerMode()
        views.homeDrawerHeaderDebugView.debouncedClicks {
            sharedActionViewModel.post(HomeActivitySharedAction.CloseDrawer)
            navigator.openDebug(requireActivity())
        }

        API.getPoints(session.myUserId)

        API.points.observe(viewLifecycleOwner, Observer {
            it?.let {
                views.score.text = StringBuilder().append(it).append(" נקודות")
            } ?: run {
                views.score.text = "0 נקודות"
            }

            views.role.text = API.rank
        })
    }
}
