package de.berlindroid.droidcon.droidconcommunity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val CAMERA_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        applyButton.setOnClickListener { apply() }

        main_button_picture.setOnClickListener { takePicture() }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {

        if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            // permission was granted, yay! Do the
            // contacts-related task you need to do.
        } else {
            // permission denied, boo! Disable the
            // functionality that depends on this permission.
        }
        return
    }

    private fun takePicture() {
        //TODO :)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startActivity(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
        }
    }

    private fun apply() {
        if (validate()) {
            //TODO: save a data
        }
    }

    private fun validate(): Boolean {
        return layoutName.validateEmptyState() && layoutLastName.validateEmptyState()
    }
}

private fun TextInputLayout.validateEmptyState(): Boolean {
    return if (editText!!.text.isNullOrBlank()) {
        error = "Please fill in"
        false
    } else true
}