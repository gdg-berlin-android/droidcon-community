/*
 * Copyright 2018 GDG Community
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package de.berlindroid.droidcon.droidconcommunity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*


// TODO: please use all your language skills to contribute translations!
class MainActivity : AppCompatActivity() {
    companion object {
        private const val CAMERA_REQUEST_CODE = 1
        private const val CAMERA_RESULT_REQUEST_CODE = 2
        private var counter = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        applyButton.setOnClickListener { apply() }

        main_button_picture.setOnClickListener { takePicture() }
        main_button_schedule.setOnClickListener { goToSchedule() }
        tweet_dc.setOnClickListener({
            Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/droidconBerlin"))
                    .also { startActivity(it) }
        })

        age_seek.max = 1_000_000
        age_seek.progress = 30

        val maxAge = 120 // years
        age_seek.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                if (progress != 0) {
                    age.text = (maxAge / progress.toFloat()).toString()
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_RESULT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data!!.extras.get("data") as Bitmap
            imagePreview.setImageBitmap(imageBitmap)
        }
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
            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA_RESULT_REQUEST_CODE)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
        }
    }

    private fun tweetDCBerlin() {
        val intent: Intent? = null
        packageManager.getPackageInfo("com.twitter.android", 0)
        TODO()
    }

    private fun goToSchedule() {
        // TODO restore the counter on resume
        if (counter === 3) {
            counter = 0
            // TODO: the image is not positioned well
            imagePreview.setImageDrawable(getDrawable(R.drawable.cat))
        } else {
            // TODO: call the actual activity
            ++counter
            val intent = Intent(this, ScheduleActivity::class.java)
            startActivity(intent)
        }
    }

    private fun apply() {
        if (validate()) {
            //TODO: save a data
            val checkboxState = acceptTermsCheckbox.isChecked
            val name = nameinput.text.toString()
            val lastname = lastNameinput.text.toString()

            val prefManager = PreferenceManager.getDefaultSharedPreferences(this)
            prefManager.edit().putString("name", name).putString("lastname", lastname).apply()
        }
    }

    private fun validate(): Boolean {
        return layoutName.validateEmptyState() && layoutLastName.validateEmptyState()
    }
}

private fun TextInputLayout.validateEmptyState(): Boolean {
    return if (editText!!.text.isNullOrBlank()) {
        error = context.getString(R.string.main_input_empty_error)
        false
    } else true
}