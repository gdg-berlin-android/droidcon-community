package de.berlindroid.droidcon.droidconcommunity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
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
        api.getSessions().enqueue(object: retrofit2.Callback<List<Session>> {

            override fun onResponse(call: Call<List<Session>>?, response: Response<List<Session>>?) {
                response?.body()?.forEach {
                    // TODO: Add the stuff to the adapter
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

    class ScheduleAdapter: RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

        class ScheduleViewHolder(view: View) {

            fun bind(data: de.berlindroid.droidcon.droidconcommunity.Session) {

            }

        }

    }

}