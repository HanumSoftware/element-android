/*
 * Copyright (c) 2021 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.app.features.about_us

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.facebook.appevents.AppEventsLogger
import com.google.android.material.bottomsheet.BottomSheetBehavior
import im.vector.app.API
import im.vector.app.LogEventBody
import im.vector.app.R
import im.vector.app.core.di.DefaultSharedPreferences

class CandidatesActivity : AppCompatActivity() {

    private var bottomSheet: BottomSheetBehavior<View>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidates)

        AppEventsLogger.newLogger(this).logEvent("MK")

        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        bottomSheet = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet))
        bottomSheet?.isDraggable = true

        findViewById<RelativeLayout>(R.id.topArrowParent).setOnClickListener {
            bottomSheet?.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        /** IMAGES **/

        findViewById<ImageView>(R.id.candidate1).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkBio")
            findViewById<TextView>(R.id.info_text).text = getString(R.string.gidon_info)
            bottomSheet?.state = BottomSheetBehavior.STATE_EXPANDED
            bioEvent()
        }
        findViewById<ImageView>(R.id.candidate2).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkBio")
            findViewById<TextView>(R.id.info_text).text = getString(R.string.mishel_info)
            bottomSheet?.state = BottomSheetBehavior.STATE_EXPANDED
            bioEvent()
        }
        findViewById<ImageView>(R.id.candidate3).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkBio")

            findViewById<TextView>(R.id.info_text).text = getString(R.string.meir_info)
            bottomSheet?.state = BottomSheetBehavior.STATE_EXPANDED
            bioEvent()
        }
        findViewById<ImageView>(R.id.candidate4).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkBio")

            findViewById<TextView>(R.id.info_text).text = getString(R.string.michal_info)
            bottomSheet?.state = BottomSheetBehavior.STATE_EXPANDED
            bioEvent()
        }
        findViewById<ImageView>(R.id.candidate5).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkBio")

            findViewById<TextView>(R.id.info_text).text = getString(R.string.dani_info)
            bottomSheet?.state = BottomSheetBehavior.STATE_EXPANDED
            bioEvent()
        }
        findViewById<ImageView>(R.id.candidate6).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkBio")

            findViewById<TextView>(R.id.info_text).text = getString(R.string.zvi_info)
            bottomSheet?.state = BottomSheetBehavior.STATE_EXPANDED
            bioEvent()
        }
        findViewById<ImageView>(R.id.candidate7).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkBio")

            findViewById<TextView>(R.id.info_text).text = getString(R.string.michal_shir_info)
            bottomSheet?.state = BottomSheetBehavior.STATE_EXPANDED
            bioEvent()
        }
        findViewById<ImageView>(R.id.candidate8).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkBio")

            findViewById<TextView>(R.id.info_text).text = getString(R.string.sheran_info)
            bottomSheet?.state = BottomSheetBehavior.STATE_EXPANDED
            bioEvent()
        }
        findViewById<ImageView>(R.id.candidate9).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkBio")

            findViewById<TextView>(R.id.info_text).text = getString(R.string.ifat_info)
            bottomSheet?.state = BottomSheetBehavior.STATE_EXPANDED
            bioEvent()
        }
        findViewById<ImageView>(R.id.candidate10).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkBio")

            findViewById<TextView>(R.id.info_text).text = getString(R.string.zeev_info)
            bottomSheet?.state = BottomSheetBehavior.STATE_EXPANDED
            bioEvent()
        }
        findViewById<ImageView>(R.id.candidate11).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkBio")

            findViewById<TextView>(R.id.info_text).text = getString(R.string.yoaz_info)
            bottomSheet?.state = BottomSheetBehavior.STATE_EXPANDED
            bioEvent()
        }
        findViewById<ImageView>(R.id.candidate12).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkBio")

            findViewById<TextView>(R.id.info_text).text = getString(R.string.beni_info)
            bottomSheet?.state = BottomSheetBehavior.STATE_EXPANDED
            bioEvent()
        }
        findViewById<ImageView>(R.id.candidate13).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkBio")

            findViewById<TextView>(R.id.info_text).text = getString(R.string.avi_info)
            bottomSheet?.state = BottomSheetBehavior.STATE_EXPANDED
            bioEvent()
        }
        findViewById<ImageView>(R.id.candidate14).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkBio")

            findViewById<TextView>(R.id.info_text).text = getString(R.string.hila_info)
            bottomSheet?.state = BottomSheetBehavior.STATE_EXPANDED
            bioEvent()
        }
        findViewById<ImageView>(R.id.candidate15).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkBio")

            findViewById<TextView>(R.id.info_text).text = getString(R.string.ofer_info)
            bottomSheet?.state = BottomSheetBehavior.STATE_EXPANDED
            bioEvent()
        }

        /** LINKS **/
        findViewById<ImageView>(R.id.candidate1_link).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkChat")
            openLink("https://matrix.to/#/!dgYSLtcCubxzVjTgiU:gidonline?via=gidonline")
        }
        findViewById<ImageView>(R.id.candidate2_link).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkChat")
            openLink("https://matrix.to/#/!CFroYIOQgHgBKtZRtc:gidonline?via=gidonline")
        }
        findViewById<ImageView>(R.id.candidate3_link).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkChat")
            openLink("https://matrix.to/#/!QLWpBULMHLkuSVHxwY:gidonline?via=gidonline")
        }
        findViewById<ImageView>(R.id.candidate4_link).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkChat")
            openLink("https://matrix.to/#/!OZxLOuZcIrwVGvqtZz:gidonline?via=gidonline")
        }
        findViewById<ImageView>(R.id.candidate5_link).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkChat")
            openLink("https://matrix.to/#/!rlVLNgKfGWctZjOMwO:gidonline?via=gidonline")
        }
        findViewById<ImageView>(R.id.candidate6_link).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkChat")
            openLink("https://matrix.to/#/!AUpwfXqfovBUxerqmw:gidonline?via=gidonline")
        }
        findViewById<ImageView>(R.id.candidate7_link).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkChat")
            openLink("https://matrix.to/#/!OKNGlZruIxtcTvGAqp:gidonline?via=gidonline")
        }
        findViewById<ImageView>(R.id.candidate8_link).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkChat")
            openLink("https://matrix.to/#/!LWeWFYTcvJvQaIYbHa:gidonline?via=gidonline")
        }
        findViewById<ImageView>(R.id.candidate9_link).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkChat")
            openLink("https://matrix.to/#/!qsKbUTLlhlSlTFapEi:gidonline?via=gidonline")
        }
        findViewById<ImageView>(R.id.candidate10_link).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkChat")
            openLink("https://matrix.to/#/!UBAWWuHlfAwrDxjymD:gidonline?via=gidonline")
        }
        findViewById<ImageView>(R.id.candidate11_link).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkChat")
            openLink("https://matrix.to/#/!oKsqTeclpWuPjyvVgl:gidonline?via=gidonline")
        }
        findViewById<ImageView>(R.id.candidate12_link).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkChat")
            openLink("https://matrix.to/#/!VyKrlQfnnebbwWoaNF:gidonline?via=gidonline")
        }
        findViewById<ImageView>(R.id.candidate13_link).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkChat")
            openLink("https://matrix.to/#/!rajrUEJXRiebfBeYjj:gidonline?via=gidonline")
        }
        findViewById<ImageView>(R.id.candidate14_link).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkChat")
            openLink("https://matrix.to/#/!EoajsHsNurMEteIdgu:gidonline?via=gidonline")
        }
        findViewById<ImageView>(R.id.candidate15_link).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("MkChat")
            openLink("https://matrix.to/#/!HMgbiqOgydWmpEbFlS:gidonline?via=gidonline")
        }
    }

    private fun bioEvent() {
        API.performLogEvent(LogEventBody("candidate", "${intent.getStringExtra("userId")}", 9))

//        AppEventsLogger
//                .newLogger(this)
//                .logEvent(
//                        "candidate",
//                        9.0,
//                        Bundle().apply {
//                            putInt("${intent.getStringExtra("userId")}", 9)
//                        }
//                )

    }

    private fun openLink(link: String) {
        val prefs = DefaultSharedPreferences.getInstance(this)

        if (prefs.getBoolean("candidatechat_first", true)) {

            with(prefs.edit()) {
                putBoolean("candidatechat_first", false)
                apply()
            }

            API.performLogEvent(LogEventBody("candidatechat_first", "${intent.getStringExtra("userId")}", 20))

//            AppEventsLogger
//                    .newLogger(this)
//                    .logEvent(
//                            "candidatechat_first",
//                            20.0,
//                            Bundle().apply {
//                                putInt("${intent.getStringExtra("userId")}", 20)
//                            }
//                    )
        } else {
            API.performLogEvent(LogEventBody("candidatechat", "${intent.getStringExtra("userId")}", 10))

//            AppEventsLogger
//                    .newLogger(this)
//                    .logEvent(
//                            "candidatechat",
//                            10.0,
//                            Bundle().apply {
//                                putInt("${intent.getStringExtra("userId")}", 10)
//                            }
//                    )
        }

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}
