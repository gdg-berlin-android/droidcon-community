package de.berlindroid.droidcon.droidconcommunity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        applyButton.setOnClickListener { apply() }
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
