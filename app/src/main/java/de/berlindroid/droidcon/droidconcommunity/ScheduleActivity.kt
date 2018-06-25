package de.berlindroid.droidcon.droidconcommunity

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import info.metadude.kotlin.library.droidconberlin.ApiModule
import info.metadude.kotlin.library.droidconberlin.ApiService
import info.metadude.kotlin.library.droidconberlin.models.Session
import kotlinx.android.synthetic.main.activity_schedule.*
import okhttp3.Callback
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response

typealias DroidconAPI = ApiService

class ScheduleActivity : AppCompatActivity(){

    val apiUrl = "https://cfp.droidcon.de/"

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_schedule)

        val okHttpClient = OkHttpClient.Builder().build()
        val api = ApiModule.provideApiService(apiUrl, okHttpClient)
        val adapter = ScheduleAdapter(this)
        api.getSessions().enqueue(object: retrofit2.Callback<List<Session>> {

            override fun onResponse(call: Call<List<Session>>?, response: Response<List<Session>>?) {
                response?.body()?.forEach {
                    adapter.clear()
//                    adapter.addAll(response.body()) TODO transform session entitz to session model and then add all. good luck have fun

                }
            }

            override fun onFailure(call: Call<List<Session>>?, t: Throwable?) {
                //we don't care :)
            }

        })

//        schedule_recycler_view.adapter
        //The idea is to have the schedule, we have a library which provide the Schedule of the Droidcon already imported.
    }

    //TODO: Write an adapter here

    class ScheduleAdapter constructor(var context: Context): RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
        lateinit var list: MutableList<de.berlindroid.droidcon.droidconcommunity.Session>

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

        fun addAll(collection: Collection<de.berlindroid.droidcon.droidconcommunity.Session>) {
            list.addAll(collection)
        }

        class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(data: de.berlindroid.droidcon.droidconcommunity.Session) {

            }

        }

    }

}