/*
 * Copyright 2018 GDG Community
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, ` distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package de.berlindroid.droidcon.droidconcommunity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.metadude.kotlin.library.droidconberlin.ApiModule
import info.metadude.kotlin.library.droidconberlin.ApiService
import info.metadude.kotlin.library.droidconberlin.models.Session
import kotlinx.android.synthetic.main.activity_schedule.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response

typealias DroidconAPI = ApiService

class ScheduleActivity : AppCompatActivity() {

    val apiUrl = "https://cfp.droidcon.de/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        val okHttpClient = OkHttpClient.Builder().build()
        val api = ApiModule.provideApiService(apiUrl, okHttpClient)
        val adapter = ScheduleAdapter(this)
        api.getSessions().enqueue(object : retrofit2.Callback<List<Session>> {

            override fun onResponse(call: Call<List<Session>>?, response: Response<List<Session>>?) {
                response?.body()?.forEach {
                    adapter.clear()
                    adapter.addAll(transformSession(response.body()!!))
                    adapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<List<Session>>?, t: Throwable?) {
                //we don't care :)
            }

        })

        schedule_recycler_view.adapter = adapter
        schedule_recycler_view.layoutManager = LinearLayoutManager(this)
        //The idea is to have the schedule, we have a library which provide the Schedule of the Droidcon already imported.
    }

    private fun transformSession(apiSessions: List<Session>): List<de.berlindroid.droidcon.droidconcommunity.Session> {
        return apiSessions.map {
            de.berlindroid.droidcon.droidconcommunity.Session().apply {
                this.title = it.title
            }
        }
    }


    class ScheduleAdapter constructor(var context: Context) : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
        val list: MutableList<de.berlindroid.droidcon.droidconcommunity.Session> = mutableListOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
            return ScheduleViewHolder(LayoutInflater.from(context).inflate(R.layout.item_session, parent, false))
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
            holder.bind(list.get(position))
        }

        fun clear() {
            list.clear()
        }

        fun addAll(sessions: List<de.berlindroid.droidcon.droidconcommunity.Session>) {
            list.addAll(sessions)
        }

        class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(data: de.berlindroid.droidcon.droidconcommunity.Session) {
                itemView.findViewById<TextView>(R.id.sessionName).text = data.title
            }

        }

    }

}