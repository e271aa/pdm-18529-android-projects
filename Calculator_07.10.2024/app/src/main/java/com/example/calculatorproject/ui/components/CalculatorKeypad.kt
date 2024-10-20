package com.example.calculatorproject.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorKeypad(
    onDigitClick: (String) -> Unit,
    onOperationClick: (String) -> Unit,
    onEqualsClick: () -> Unit,
    onClearClick: () -> Unit,
    onEraseClick: () -> Unit,
    onMemoryRecall: () -> Unit,
    onMemoryAdd: () -> Unit,
    onMemorySubtract: () -> Unit,
    onSquareRoot: () -> Unit,
    onPercentage: () -> Unit,
    onSignChange: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        CalculatorRow {
            CalculatorButton("MRC", Modifier.weight(1f), onClick = onMemoryRecall, backgroundColor = Color(0xFF222121))
            CalculatorButton("M-", Modifier.weight(1f), onClick = onMemorySubtract, backgroundColor = Color(0xFF222121))
            CalculatorButton("M+", Modifier.weight(1f), onClick = onMemoryAdd, backgroundColor = Color(0xFF222121))
            CalculatorButton("ON/C", Modifier.weight(1f), onClick = onEraseClick, backgroundColor = Color(0xFFD1546F))
        }
        CalculatorRow {
            CalculatorButton("√", Modifier.weight(1f), onClick = onSquareRoot, backgroundColor = Color(0xFF222121))
            CalculatorButton("%", Modifier.weight(1f), onClick = onPercentage, backgroundColor = Color(0xFF222121))
            CalculatorButton("±", Modifier.weight(1f), onClick = onSignChange, backgroundColor = Color(0xFF222121))
            CalculatorButton("CE", Modifier.weight(1f), onClick = onClearClick, backgroundColor = Color(0xFFD1546F))
        }
        CalculatorRow {
            CalculatorButton("7", Modifier.weight(1f), onClick = { onDigitClick("7") })
            CalculatorButton("8", Modifier.weight(1f), onClick = { onDigitClick("8") })
            CalculatorButton("9", Modifier.weight(1f), onClick = { onDigitClick("9") })
            CalculatorButton("÷", Modifier.weight(1f), onClick = { onOperationClick("÷") }, backgroundColor = Color(0xFF222121))
        }
        CalculatorRow {
            CalculatorButton("4", Modifier.weight(1f), onClick = { onDigitClick("4") })
            CalculatorButton("5", Modifier.weight(1f), onClick = { onDigitClick("5") })
            CalculatorButton("6", Modifier.weight(1f), onClick = { onDigitClick("6") })
            CalculatorButton("×", Modifier.weight(1f), onClick = { onOperationClick("×") }, backgroundColor = Color(0xFF222121))
        }
        CalculatorRow {
            CalculatorButton("1", Modifier.weight(1f), onClick = { onDigitClick("1") })
            CalculatorButton("2", Modifier.weight(1f), onClick = { onDigitClick("2") })
            CalculatorButton("3", Modifier.weight(1f), onClick = { onDigitClick("3") })
            CalculatorButton("-", Modifier.weight(1f), onClick = { onOperationClick("-") }, backgroundColor = Color(0xFF222121))
        }
        CalculatorRow {
            CalculatorButton("0", Modifier.weight(1f), onClick = { onDigitClick("0") })
            CalculatorButton(".", Modifier.weight(1f), onClick = { onDigitClick(".") })
            CalculatorButton("=", Modifier.weight(1f), onClick = onEqualsClick)
            CalculatorButton("+", Modifier.weight(1f), onClick = { onOperationClick("+") }, backgroundColor = Color(0xFF222121))
        }
    }
}

@Composable
fun CalculatorRow(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = content
    )
}