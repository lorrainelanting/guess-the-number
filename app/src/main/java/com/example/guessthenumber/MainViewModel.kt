package com.example.guessthenumber

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var bottom = 0
    var top = 100
    private var hiddenNumber = -1

    fun start() {
        bottom = 0
        top = 100
        hiddenNumber = (bottom..top).random() // generate random number
    }

    fun guess(userInput: Int): Int {
        if (hiddenNumber == userInput) {
            return 0
        }

        // Assign value to Bottom
        if (userInput < hiddenNumber) {
            if (userInput > bottom) {
                bottom = userInput
            }
            return -1
        }

        // Assign value to Top
        if (userInput > hiddenNumber) {
            if (userInput < top) {
                top = userInput
            }
            return 1
        }

        return 2
    }
}