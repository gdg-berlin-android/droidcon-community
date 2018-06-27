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

package de.berlindroid.droidcon.droidconcommunity.hangman

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import de.berlindroid.droidcon.droidconcommunity.R
import java.lang.StringBuilder

class HangmanActivity : AppCompatActivity() {

    val maxlifes = 5
    val solutions = listOf("droidcon", "android", "tensorflow", "kittens", "databinding", "gdg", "random")

    var target: String = ""
    var displayTarget = ""

    lateinit var progressText: TextView

    fun reset() {
        target = solutions.shuffled().first()
        displayTarget = target.map { "▉" }.joinToString(separator = ",")
        progressText.text = displayTarget
        lifeCount = maxlifes
        keyboard.values.forEach {
             it.isEnabled = true
        }
    }

    private val keyboard by lazy {
        mutableMapOf<String, Button>().apply {
            fun String.addButton() {
                put(this, createButton())
            }

            "Q".addButton()
            "W".addButton()
            "E".addButton()
            "R".addButton()
            "T".addButton()
            "Y".addButton()
            "U".addButton()
            "I".addButton()
            "O".addButton()
            "P".addButton()
            "A".addButton()
            "S".addButton()
            "D".addButton()
            "F".addButton()
            "G".addButton()
            "H".addButton()
            "J".addButton()
            "K".addButton()
            "K".addButton()
            "L".addButton()
            "Z".addButton()
            "X".addButton()
            "C".addButton()
            "V".addButton()
            "B".addButton()
            "N".addButton()
            "M".addButton()
        }
    }

    var lifeCount = maxlifes
        set(value) {
            field = value
            updateUI()
        }


    private fun String.createButton() = Button(this@HangmanActivity).apply {
        text = this@createButton
        setOnClickListener { characterPressed(this, this@createButton) }
    }

    fun characterPressed(button: View, character: String) {
        button.isEnabled = false
        val differentName = character.toLowerCase()
        if (target.contains(differentName)) {
            val indexes = mutableListOf<Int>()
            target.forEachIndexed { index, c -> if (c.toString() == differentName) indexes.add(index) }
            val valami = StringBuilder(displayTarget)
            indexes.forEach {
                valami[it * 2] = differentName.first()
            }
            displayTarget = valami.toString()
            progressText.text = displayTarget

            if (!displayTarget.contains("▉")) {
                Toast.makeText(this, "YOU WON 🏆", Toast.LENGTH_LONG).show()
                reset()
            }
        } else {
            lifeCount--
            if (lifeCount == 0) {
                Toast.makeText(this, "YOU LOST 😭", Toast.LENGTH_LONG).show()
                reset()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hangman)

        progressText = findViewById(R.id.progress)
        findViewById<GridLayout>(R.id.keyboard).apply {
            keyboard.forEach { (_, button) ->
                addView(button)
            }
        }

        savedInstanceState?.let { reset() }
        reset()
        findViewById<ImageView>(R.id.image).setOnClickListener({

            lifeCount--
            if (lifeCount < 0) lifeCount = 5

            updateUI()


        })
    }

    fun updateUI() {
        findViewById<ImageView>(R.id.image).setImageResource(
                when (lifeCount) {
                    0 -> R.drawable.hang5
                    1 -> R.drawable.hang4
                    2 -> R.drawable.hang3
                    3 -> R.drawable.hang2
                    4 -> R.drawable.hang1
                    else -> R.drawable.hang0
                }
        )
    }
}