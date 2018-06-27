package de.berlindroid.droidcon.droidconcommunity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintro.AppIntroFragment
import com.github.paolorotolo.appintro.model.SliderPage
import de.berlindroid.droidcon.droidconcommunity.hangman.HangmanActivity

class IntroActivity : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val title = resources.getString(R.string.app_name)
        val firstSlide = SliderPage().apply {
            this.title = title
            description = getString(R.string.intro_1)
            imageDrawable = R.drawable.cat
            bgColor = resources.getColor(R.color.colorPrimary)
        }
        val firstScreenFragment = AppIntroFragment.newInstance(firstSlide)
        addSlide(firstScreenFragment)

        Handler().postDelayed({
            Glide.with(this)
                    .load("https://media3.giphy.com/media/sIIhZliB2McAo/giphy.gif")
                    .into(firstScreenFragment.view?.findViewById(R.id.image)!!)

        }, 1000)

        addSlide(AppIntroFragment.newInstance(title, getString(R.string.intro_2), R.drawable.cat, resources.getColor(R.color.colorPrimary)))
        addSlide(AppIntroFragment.newInstance(title, getString(R.string.intro_3), R.drawable.cat, resources.getColor(R.color.colorPrimary)))
        addSlide(AppIntroFragment.newInstance(title, getString(R.string.intro_4), R.drawable.cat, resources.getColor(R.color.colorPrimary)))
        addSlide(AppIntroFragment.newInstance(title, getString(R.string.intro_5), R.drawable.cat, resources.getColor(R.color.colorPrimary)))

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

        showSkipButton(true)
        setProgressButtonEnabled(true)

        //TODO enable go to next screen!
    }


    override fun onSkipPressed(currentFragment: Fragment) {
        super.onSkipPressed(currentFragment)
        // Do something when users tap on Skip button.
        startActivity(Intent(this, HangmanActivity::class.java))
    }

    override fun onDonePressed(currentFragment: Fragment) {
        super.onDonePressed(currentFragment)
        // Do something when users tap on Done button.
        startActivity(Intent(this, MainActivity::class.java))
    }
}