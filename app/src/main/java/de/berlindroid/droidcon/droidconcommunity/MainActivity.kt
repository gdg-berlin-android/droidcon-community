package de.berlindroid.droidcon.droidconcommunity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        /*Log.d("ainActivity", nameTV.text.toString())
        Log.d("MainActivity", lastnametv.text.toString())*/

        //MESSAGE TO FUTURE you can add it to the db
        applyButton.setOnClickListener { apply() }
    }

    private fun apply() {

    }
}
