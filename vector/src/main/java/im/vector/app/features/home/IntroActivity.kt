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

package im.vector.app.features.home

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.facebook.appevents.AppEventsLogger
import im.vector.app.API
import im.vector.app.LogEventBody
import im.vector.app.R
import im.vector.app.core.di.DefaultSharedPreferences

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val userId = intent.getStringExtra("userId")

        val prefs = DefaultSharedPreferences.getInstance(this)

        if (prefs.getBoolean("isFirstTime", true)) {

            with(prefs.edit()) {
                putBoolean("isFirstTime", false)
                apply()
            }

            API.performLogEvent(LogEventBody("activate_first", "${userId}", 5))

//            AppEventsLogger
//                    .newLogger(this)
//                    .logEvent(
//                            "activate_first",
//                            5.0,
//                            Bundle().apply {
//                                putInt("${userId}", 5)
//                            }
//                    )
        } else {
            API.performLogEvent(LogEventBody("activate", "${userId}", 1))

//            AppEventsLogger
//                    .newLogger(this)
//                    .logEvent(
//                            "activate",
//                            1.0,
//                            Bundle().apply {
//                                putInt("${userId}", 1)
//                            }
//                    )
        }

        val state = intent.getBooleanExtra("signMode", false)

        findViewById<Button>(R.id.continue_btn).setOnClickListener {

            API.performLogEvent(LogEventBody("viewcontent", "${userId}", 1))

//            AppEventsLogger
//                    .newLogger(this)
//                    .logEvent(
//                            "viewcontent",
//                            1.0,
//                            Bundle().apply {
//                                putInt("${userId}", 1)
//                            }
//                    )

            val intent = HomeActivity.newIntent(
                    this,
                    accountCreation = state
            )
            startActivity(intent)
            finish()
        }
    }
}
