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
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.facebook.appevents.AppEventsLogger
import im.vector.app.API
import im.vector.app.LogEventBody
import im.vector.app.R
import im.vector.app.core.di.DefaultSharedPreferences

class PrinciplesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principles)

        AppEventsLogger.newLogger(this).logEvent("7Principles")

        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        findViewById<ConstraintLayout>(R.id.priciple1_btn).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("7PrinciplesChannel")
            openLink("https://matrix.to/#/!MzaSQyelimGwwvXOki:gidonline?via=gidonline")
        }
        findViewById<ConstraintLayout>(R.id.priciple2_btn).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("7PrinciplesChannel")
            openLink("https://matrix.to/#/!CjqSpYNQWoEVyBtOGO:gidonline?via=gidonline")
        }
        findViewById<ConstraintLayout>(R.id.priciple3_btn).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("7PrinciplesChannel")
            openLink("https://matrix.to/#/!tYRkuyPfBMhjIOqwJJ:gidonline?via=gidonline")
        }
        findViewById<ConstraintLayout>(R.id.priciple4_btn).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("7PrinciplesChannel")
            openLink("https://matrix.to/#/!VXhgtGCCeQzCAJYzUh:gidonline?via=gidonline")
        }
        findViewById<ConstraintLayout>(R.id.priciple5_btn).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("7PrinciplesChannel")
            openLink("https://matrix.to/#/!TTaZipISrfwhoQROEc:gidonline?via=gidonline")
        }
        findViewById<ConstraintLayout>(R.id.priciple6_btn).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("7PrinciplesChannel")
            openLink("https://matrix.to/#/!VABkblWQLczuWXgxOS:gidonline?via=gidonline")
        }
        findViewById<ConstraintLayout>(R.id.priciple7_btn).setOnClickListener {
            AppEventsLogger.newLogger(this).logEvent("7PrinciplesChannel")
            openLink("https://matrix.to/#/!EDMdncbIQHPTpwFLfN:gidonline?via=gidonline")
        }
    }

    private fun openLink(link: String) {
        val prefs = DefaultSharedPreferences.getInstance(this)

        if (prefs.getBoolean("7principleschat_first", true)) {

            with(prefs.edit()) {
                putBoolean("7principleschat_first", false)
                apply()
            }

            API.performLogEvent(LogEventBody("7principleschat_first", "${intent.getStringExtra("userId")}", 20))

//            AppEventsLogger
//                    .newLogger(this)
//                    .logEvent(
//                            "7principleschat_first",
//                            20.0,
//                            Bundle().apply {
//                                putInt("${intent.getStringExtra("userId")}", 20)
//                            }
//                    )
        } else {

            API.performLogEvent(LogEventBody("7principleschat", "${intent.getStringExtra("userId")}", 10))
//
//            AppEventsLogger
//                    .newLogger(this)
//                    .logEvent(
//                            "7principleschat",
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
