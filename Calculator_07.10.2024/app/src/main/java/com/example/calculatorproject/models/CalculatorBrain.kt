package com.example.calculatorproject.models

object CalculatorBrain {
    fun calculate(operand1: Double, operand2: Double, operation: String): Double {
        return when (operation) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "×" -> operand1 * operand2
            "÷" -> if (operand2 != 0.0) operand1 / operand2 else Double.NaN
            else -> Double.NaN
        }
    }
}