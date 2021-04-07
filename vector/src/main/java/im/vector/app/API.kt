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

package im.vector.app

import android.util.Log
import androidx.lifecycle.MutableLiveData
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

object API {

    val client = OkHttpClient()
    val points = MutableLiveData<String?>(null)
    var rank : String? = null
    var displayName: String? = null
    var mediaId: String? = null

    fun performLogEvent(log: LogEventBody) {
        log.display_name = displayName
        log.media_id = mediaId
        val request: Request = Request.Builder()
                .url("https://identity.gidonline.e-lekt.com:4333/add_event")
                .post(log.toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull()))
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("performLogEvent", "onFailure")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("performLogEvent", "onResponse")
            }
        })
    }

    fun getPoints(userId: String) {
        val request: Request = Request.Builder()
                .url("https://identity.gidonline.e-lekt.com:4333/get_points/$userId")
                .get()
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("performLogEvent", "onFailure")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.code == 200) {
                    Log.d("performLogEvent", "onResponse")
                    response.body?.string()?.let {
                        points.postValue(JSONObject(it)["points"].toString())
                        rank = JSONObject(it)["rank"].toString()
                    }
                }
            }
        })
    }
}
