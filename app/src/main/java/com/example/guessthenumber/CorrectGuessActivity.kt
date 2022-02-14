package com.example.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CorrectGuessActivity : AppCompatActivity() {
    companion object {
        const val CORRECT_GUESS = "CORRECT_GUESS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_correct_guess)
    }
}