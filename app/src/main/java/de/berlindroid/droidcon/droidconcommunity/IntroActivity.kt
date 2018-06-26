package de.berlindroid.droidcon.droidconcommunity

import android.content.Intent
import android.graphics.Color
import com.github.paolorotolo.appintro.AppIntroFragment
import android.os.Bundle
import com.github.paolorotolo.appintro.AppIntro

import android.support.v4.app.Fragment

class IntroActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance(title, "Welcome to our awesome community app :)", R.drawable.cat, resources.getColor(R.color.colorPrimary)))
        addSlide(AppIntroFragment.newInstance(title, "This app is proudly created by the android devs at #DCBERLIN18", R.drawable.cat, resources.getColor(R.color.colorPrimary)))
        addSlide(AppIntroFragment.newInstance(title, "Want to do what this app is doing...?", R.drawable.cat, resources.getColor(R.color.colorPrimary)))
        addSlide(AppIntroFragment.newInstance(title, "¯\\_(ツ)_/¯", R.drawable.cat, resources.getColor(R.color.colorPrimary)))
        addSlide(AppIntroFragment.newInstance(title, "I don't know!", R.drawable.cat, resources.getColor(R.color.colorPrimary)))
        super.onCreate(savedInstanceState)

        // Instead of fragments, you can also use our default slide

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"))
        setSeparatorColor(Color.parseColor("#2196F3"))

        // Hide Skip/Done button.
        showSkipButton(true)
        isProgressButtonEnabled = false

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true)
        setVibrateIntensity(30)
    }

    override fun onSkipPressed(currentFragment: Fragment) {
        super.onSkipPressed(currentFragment)
        // Do something when users tap on Skip button.
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onDonePressed(currentFragment: Fragment) {
        super.onDonePressed(currentFragment)
        // Do something when users tap on Done button.
        startActivity(Intent(this, MainActivity::class.java))
    }
}