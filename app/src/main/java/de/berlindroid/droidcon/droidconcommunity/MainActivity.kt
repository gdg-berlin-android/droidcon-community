package de.berlindroid.droidcon.droidconcommunity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        applyButton.setOnClickListener { apply() }

        main_button_picture.setOnClickListener{ takePicture()}
    }

    private fun takePicture(){
        //TODO :)
        //TODO if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        startActivity(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }

    private fun apply() {
        if(validate()){
            //TODO: save a data
        }
    }

    private fun validate() =
            layoutName.validateEmptyState()
                && layoutLastName.validateEmptyState()
}

private fun TextInputLayout.validateEmptyState():Boolean {
    val editText = getChildAt(0) as? TextInputEditText ?: return false
    return if (editText.text.isNullOrBlank()) {
        error = "Please fill in"
        false
    } else true
}