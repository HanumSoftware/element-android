/*
 * Copyright 2019 New Vector Ltd
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

package im.vector.app.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import im.vector.app.R
import im.vector.app.databinding.FragmentLoginSplashBinding

import javax.inject.Inject

/**
 * In this screen, the user is viewing an introduction to what he can do with this application
 */
class LoginSplashFragment @Inject constructor() : AbstractLoginFragment<FragmentLoginSplashBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginSplashBinding {
        return FragmentLoginSplashBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setupViews()
        loginViewModel.handle(LoginAction.PostViewEvent(LoginViewEvents.OpenServerSelection))
    }

    private fun setupViews() {
        views.loginSplashSubmit.setOnClickListener { getStarted() }
    }

    private fun getStarted() {
        loginViewModel.handle(LoginAction.PostViewEvent(LoginViewEvents.OpenServerSelection))
    }

    override fun resetViewModel() {
        // Nothing to do
    }
}
