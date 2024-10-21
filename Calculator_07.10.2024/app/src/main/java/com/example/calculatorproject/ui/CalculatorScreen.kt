package com.example.calculatorproject.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.calculatorproject.models.CalculatorBrain
import com.example.calculatorproject.ui.components.*

@Composable
fun CalculatorScreen() {
    var display by remember { mutableStateOf("0") }
    var currentOperation by remember { mutableStateOf<String?>(null) }
    var firstOperand by remember { mutableStateOf<Double?>(null) }
    var memory by remember { mutableStateOf(0.0) }
    var shouldResetDisplay by remember { mutableStateOf(false) }

    fun updateDisplay(digit: String) {
        display = if (shouldResetDisplay) {
            shouldResetDisplay = false
            digit
        } else {
            if (display == "0" && digit != ".") digit else display + digit
        }
    }

    fun resetCalculator() {
        display = "0"
        firstOperand = null
        currentOperation = null
        shouldResetDisplay = false
    }

    fun calculate() {
        val secondOperand = display.toDoubleOrNull()
        if (firstOperand != null && secondOperand != null && currentOperation != null) {
            val result = CalculatorBrain.calculate(firstOperand!!, secondOperand, currentOperation!!)
            display = CalculatorBrain.formatResult(result)
            firstOperand = null
            currentOperation = null
            shouldResetDisplay = true
        }
    }

    fun setOperation(op: String) {
        val secondOperand = display.toDoubleOrNull()
        if (firstOperand != null && secondOperand != null && currentOperation != null) {
            val result = CalculatorBrain.calculate(firstOperand!!, secondOperand, currentOperation!!)
            display = CalculatorBrain.formatResult(result)
            firstOperand = result
        } else {
            firstOperand = display.toDoubleOrNull()
        }
        currentOperation = op
        shouldResetDisplay = true
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
                    onMemoryRecall = { display = CalculatorBrain.formatMemoryValue(memory) },
                    onMemoryAdd = { memory += display.toDoubleOrNull() ?: 0.0 },
                    onMemorySubtract = { memory -= display.toDoubleOrNull() ?: 0.0 },
                    onSquareRoot = {
                        display = CalculatorBrain.formatResult(
                            Math.sqrt(display.toDoubleOrNull() ?: 0.0)
                        )
                    },
                    onPercentage = {
                        val secondOperand = display.toDoubleOrNull() ?: 0.0
                        display = CalculatorBrain.formatResult(
                            CalculatorBrain.applyPercentage(firstOperand!!, secondOperand)
                        )
                        shouldResetDisplay = true
                    },
                    onSignChange = {
                        display = CalculatorBrain.formatResult(
                            CalculatorBrain.changeSign(display.toDoubleOrNull() ?: 0.0)
                        )
                    }
                )
            }
        }
    }
}