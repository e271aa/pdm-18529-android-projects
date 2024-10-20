package com.example.calculatorproject.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.calculatorproject.ui.components.*

@Composable
fun CalculatorScreen() {
    var display by remember { mutableStateOf("0") }
    var currentOperation by remember { mutableStateOf<String?>(null) }
    var firstOperand by remember { mutableStateOf<Double?>(null) }
    var memory by remember { mutableStateOf(0.0) }
    var shouldResetDisplay by remember { mutableStateOf(false) }

    fun updateDisplay(digit: String) {
        if (shouldResetDisplay) {
            display = digit
            shouldResetDisplay = false
        } else {
            display = if (display == "0" && digit != ".") digit else display + digit
        }
    }

    fun resetCalculator() {
        display = "0"
        firstOperand = null
        currentOperation = null
        shouldResetDisplay = false
    }

    fun resetCalculatorState() {
        firstOperand = null
        currentOperation = null
        shouldResetDisplay = false
    }

    fun formatMemoryValue(value: Double): String {
        return if (value % 1.0 == 0.0) {
            value.toInt().toString()
        } else {
            value.toString()
        }
    }

    fun formatResult(value: Double): String {
        val formatted = value.toString().removeSuffix(".0")
        return if (formatted.length > 9) formatted.take(9) else formatted
    }

    fun calculate() {
        val secondOperand = display.toDoubleOrNull()

        if (firstOperand != null && secondOperand != null && currentOperation != null) {
            val result = when (currentOperation) {
                "+" -> firstOperand!! + secondOperand
                "-" -> firstOperand!! - secondOperand
                "×" -> firstOperand!! * secondOperand
                "÷" -> if (secondOperand != 0.0) firstOperand!! / secondOperand else Double.NaN
                else -> secondOperand
            }
            display = formatResult(result)
            firstOperand = null
            currentOperation = null
            shouldResetDisplay = true
        }
    }

    fun setOperation(op: String) {
        val secondOperand = display.toDoubleOrNull()

        if (firstOperand != null && secondOperand != null && currentOperation != null) {
            val result = when (currentOperation) {
                "+" -> firstOperand!! + secondOperand
                "-" -> firstOperand!! - secondOperand
                "×" -> firstOperand!! * secondOperand
                "÷" -> if (secondOperand != 0.0) firstOperand!! / secondOperand else Double.NaN
                else -> secondOperand
            }
            display = formatResult(result)
            firstOperand = result
        } else {
            firstOperand = display.toDoubleOrNull()
        }

        currentOperation = op
        shouldResetDisplay = true
    }

    fun applyPercentage() {
        if (firstOperand != null && currentOperation != null) {
            val secondOperand = display.toDoubleOrNull() ?: 0.0
            val percentageValue = (firstOperand!! * secondOperand) / 100
            display = formatResult(percentageValue)
            shouldResetDisplay = true
        }
    }

    fun changeSign() {
        val value = display.toDoubleOrNull() ?: return
        display = formatResult(-value)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFAFAFAF)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CalculatorDisplay(display)
                Spacer(modifier = Modifier.height(16.dp))
                CalculatorKeypad(
                    onDigitClick = ::updateDisplay,
                    onOperationClick = ::setOperation,
                    onEqualsClick = ::calculate,
                    onEraseClick = ::resetCalculator,
                    onClearClick = { display = "0" },
                    onMemoryRecall = { display = formatMemoryValue(memory) },
                    onMemoryAdd = { memory += display.toDoubleOrNull() ?: 0.0 },
                    onMemorySubtract = { memory -= display.toDoubleOrNull() ?: 0.0 },
                    onSquareRoot = {
                        display = formatResult(Math.sqrt(display.toDoubleOrNull() ?: 0.0))
                    },
                    onPercentage = ::applyPercentage,
                    onSignChange = ::changeSign
                )
            }
        }
    }
}