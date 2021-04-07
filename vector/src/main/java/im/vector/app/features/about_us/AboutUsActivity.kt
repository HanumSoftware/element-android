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
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.facebook.appevents.AppEventsLogger
import im.vector.app.API
import im.vector.app.ContextInjector
import im.vector.app.LogEventBody
import im.vector.app.R
import im.vector.app.core.di.DefaultSharedPreferences

class AboutUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val id = intent.getStringExtra("userId")

        findViewById<Button>(R.id.our_candidates_btn).setOnClickListener {

            val prefs = ContextInjector.applicationContext?.let { DefaultSharedPreferences.getInstance(it) }

            if (prefs?.getBoolean("candidate_first", true) == true) {

                with(prefs.edit()) {
                    putBoolean("candidate_first", false)
                    apply()
                }


                API.performLogEvent(LogEventBody("candidate_first", "$id", 16))
            } else {
                API.performLogEvent(LogEventBody("candidate", "$id", 8))
            }

            val intent = Intent(this, CandidatesActivity::class.java)
            intent.putExtra("userId", id)
            startActivity(intent)
        }

        findViewById<Button>(R.id.our_principles_btn).setOnClickListener {
            val prefs = ContextInjector.applicationContext?.let { DefaultSharedPreferences.getInstance(it) }

            if (prefs?.getBoolean("7principles_first", true) == true) {

                with(prefs.edit()) {
                    putBoolean("7principles_first", false)
                    apply()
                }


                API.performLogEvent(LogEventBody("7principles_first", "$id", 7))
            } else {
                API.performLogEvent(LogEventBody("7principles", "$id", 6))
            }
            val intent = Intent(this, PrinciplesActivity::class.java)
            intent.putExtra("userId", id)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}
