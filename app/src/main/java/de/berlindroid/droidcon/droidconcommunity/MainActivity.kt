package de.berlindroid.droidcon.droidconcommunity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
    }

    private fun apply() {
        validate()
    }

    private fun validate() {
        validateEmptyState(nameinput, layoutName)
        validateEmptyState(lastNameinput, layoutLastName)
    }

    private fun validateEmptyState(editText: TextInputEditText, layout: TextInputLayout) {
        if (editText.text.isNullOrBlank()) {
            layout.error = "Please fill in"
        }
    }
}
