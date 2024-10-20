package com.example.calculatorproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorproject.ui.theme.CalculatorProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorProjectTheme {
                CalculatorScreen()
            }
        }
    }
}

fun formatResult(value: Double): String {
    val formatted = value.toString().removeSuffix(".0")
    return if (formatted.length > 9) formatted.take(9) else formatted
}

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
                    modifier = Modifier.fillMaxWidth(),
                    onDigitClick = ::updateDisplay,
                    onOperationClick = ::setOperation,
                    onEqualsClick = ::calculate,
                    onEraseClick = ::resetCalculator,
                    onClearClick = { display = "0" },
                    onMemoryRecall = { display = formatMemoryValue(memory) },
                    onMemoryAdd = { memory += display.toDoubleOrNull() ?: 0.0 },
                    onMemorySubtract = { memory -= display.toDoubleOrNull() ?: 0.0 },
                    onSquareRoot = { display = formatResult(Math.sqrt(display.toDoubleOrNull() ?: 0.0)) },
                    onPercentage = ::applyPercentage,
                    onSignChange = ::changeSign
                )
            }
        }
    }
}

@Composable
fun CalculatorDisplay(display: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color(0xFF90EE90), RoundedCornerShape(8.dp))
            .padding(8.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            text = display,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.End,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun CalculatorKeypad(
    modifier: Modifier = Modifier,
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
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CalculatorRow {
            CalculatorButton("MRC", Modifier.weight(1f), onClick = onMemoryRecall, backgroundColor = Color.Black)
            CalculatorButton("M-", Modifier.weight(1f), onClick = onMemorySubtract, backgroundColor = Color.Black)
            CalculatorButton("M+", Modifier.weight(1f), onClick = onMemoryAdd, backgroundColor = Color.Black)
            CalculatorButton("ON/C", Modifier.weight(1f), onClick = onEraseClick, backgroundColor = Color(0xFFD1546F))
        }
        CalculatorRow {
            CalculatorButton("√", Modifier.weight(1f), onClick = onSquareRoot, backgroundColor = Color.Black)
            CalculatorButton("%", Modifier.weight(1f), onClick = onPercentage, backgroundColor = Color.Black)
            CalculatorButton("±", Modifier.weight(1f), onClick = onSignChange, backgroundColor = Color.Black)
            CalculatorButton("CE", Modifier.weight(1f), onClick = onClearClick, backgroundColor = Color(0xFFD1546F))
        }
        CalculatorRow {
            CalculatorButton("7", Modifier.weight(1f), onClick = { onDigitClick("7") })
            CalculatorButton("8", Modifier.weight(1f), onClick = { onDigitClick("8") })
            CalculatorButton("9", Modifier.weight(1f), onClick = { onDigitClick("9") })
            CalculatorButton("÷", Modifier.weight(1f), onClick = { onOperationClick("÷") }, backgroundColor = Color.Black)
        }
        CalculatorRow {
            CalculatorButton("4", Modifier.weight(1f), onClick = { onDigitClick("4") })
            CalculatorButton("5", Modifier.weight(1f), onClick = { onDigitClick("5") })
            CalculatorButton("6", Modifier.weight(1f), onClick = { onDigitClick("6") })
            CalculatorButton("×", Modifier.weight(1f), onClick = { onOperationClick("×") }, backgroundColor = Color.Black)
        }
        CalculatorRow {
            CalculatorButton("1", Modifier.weight(1f), onClick = { onDigitClick("1") })
            CalculatorButton("2", Modifier.weight(1f), onClick = { onDigitClick("2") })
            CalculatorButton("3", Modifier.weight(1f), onClick = { onDigitClick("3") })
            CalculatorButton("-", Modifier.weight(1f), onClick = { onOperationClick("-") }, backgroundColor = Color.Black)
        }
        CalculatorRow {
            CalculatorButton("0", Modifier.weight(1f), onClick = { onDigitClick("0") })
            CalculatorButton(".", Modifier.weight(1f), onClick = { onDigitClick(".") })
            CalculatorButton("=", Modifier.weight(1f), onClick = onEqualsClick)
            CalculatorButton("+", Modifier.weight(1f), onClick = { onOperationClick("+") }, backgroundColor = Color.Black)
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

@Composable
fun CalculatorButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    height: Dp = 60.dp,
    backgroundColor: Color = Color(0xFF787878)
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(height),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculatorProjectTheme {
        CalculatorScreen()
    }
}
