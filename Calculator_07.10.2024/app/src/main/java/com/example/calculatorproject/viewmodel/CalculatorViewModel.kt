package com.example.calculatorproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.calculatorproject.models.CalculatorBrain

class CalculatorViewModel : ViewModel() {
    var display = "0"
        private set

    var firstOperand: Double? = null
    var currentOperation: String? = null
    var shouldResetDisplay = false

    fun updateDisplay(digit: String) {
        display = if (shouldResetDisplay) {
            shouldResetDisplay = false
            digit
        } else {
            if (display == "0" && digit != ".") digit else display + digit
        }
    }

    fun setOperation(op: String) {
        firstOperand = display.toDoubleOrNull()
        currentOperation = op
        shouldResetDisplay = true
    }

    fun calculateResult() {
        val secondOperand = display.toDoubleOrNull()
        if (firstOperand != null && secondOperand != null && currentOperation != null) {
            display = CalculatorBrain.calculate(firstOperand!!, secondOperand, currentOperation!!)
                .toString()
            firstOperand = null
            currentOperation = null
            shouldResetDisplay = true
        }
    }

    fun resetCalculator() {
        display = "0"
        firstOperand = null
        currentOperation = null
        shouldResetDisplay = false
    }
}