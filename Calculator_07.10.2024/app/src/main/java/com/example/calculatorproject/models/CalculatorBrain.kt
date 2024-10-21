package com.example.calculatorproject.models

object CalculatorBrain {

    fun formatResult(value: Double): String {
        val formatted = value.toString().removeSuffix(".0")
        return if (formatted.length > 9) formatted.take(9) else formatted
    }

    fun calculate(operand1: Double, operand2: Double, operation: String): Double {
        return when (operation) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "×" -> operand1 * operand2
            "÷" -> if (operand2 != 0.0) operand1 / operand2 else Double.NaN
            else -> Double.NaN
        }
    }

    fun changeSign(value: Double): Double = -value

    fun applyPercentage(firstOperand: Double, secondOperand: Double): Double {
        return (firstOperand * secondOperand) / 100
    }

    fun formatMemoryValue(value: Double): String {
        return if (value % 1.0 == 0.0) value.toInt().toString() else value.toString()
    }
}