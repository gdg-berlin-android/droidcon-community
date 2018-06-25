package de.berlindroid.droidcon.droidconcommunity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_schedule.*


class ScheduleActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_schedule)
//        schedule_recycler_view.adapter
        //The idea is to have the schedule, we have a library which provide the Schedule of the Droidcon already imported.
    }
}