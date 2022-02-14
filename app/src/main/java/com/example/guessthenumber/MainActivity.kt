package com.example.guessthenumber

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var okBtn: Button
    private lateinit var editText: EditText
    private lateinit var inputLayout: TextInputLayout
    private lateinit var top: TextView
    private lateinit var bottom: TextView

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        okBtn = findViewById(R.id.btn_ok)
        editText = findViewById(R.id.et_input)
        inputLayout = findViewById(R.id.text_input_layout)
        bottom = findViewById(R.id.tv_bottom_guess)
        top = findViewById(R.id.tv_top_guess)

        okBtn.setOnClickListener {
            val userInput: Int

            try {
                userInput = Integer.parseInt(editText.text.toString())
            } catch (e: Exception) {
                Toast.makeText(this, "Invalid input.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val result = viewModel.guess(userInput)
            editText.setText("") // clear edittext
            updateView()

            when (result) {
                1 -> {
                    AlertDialog.Builder(this)
                        .setMessage(R.string.top_guess_message).show()
                }
                -1 -> {
                    AlertDialog.Builder(this)
                        .setMessage(R.string.bottom_guess_message).show()
                }
                0 -> {
                    val intent = Intent(this, CorrectGuessActivity::class.java).apply {
                        putExtra(CorrectGuessActivity.CORRECT_GUESS, userInput)
                    }
                    startActivity(intent)

                    viewModel.start()
                    updateView()
                }
            }
        }
    }

    private fun updateView() {
        bottom.text = viewModel.bottom.toString()
        top.text = viewModel.top.toString()
    }

    override fun onStart() {
        super.onStart()
        viewModel.start()
    }
}