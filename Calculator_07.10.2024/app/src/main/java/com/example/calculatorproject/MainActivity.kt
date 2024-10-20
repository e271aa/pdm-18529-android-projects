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

@Composable
fun CalculatorTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF4A4A4A),
            surface = Color(0xFFD3D3D3),
            background = Color(0xFFE0E0E0)
        ),
        content = content
    )
}

@Composable
fun CalculatorScreen() {
    var display by remember { mutableStateOf("0") }
    var currentOperation by remember { mutableStateOf<String?>(null) }
    var firstOperand by remember { mutableStateOf<Double?>(null) }
    var memory by remember { mutableStateOf(0.0) }

    fun updateDisplay(digit: String) {
        display = if (display == "0" && digit != ".") digit else display + digit
    }

    fun setOperation(op: String) {
        firstOperand = display.toDoubleOrNull()
        currentOperation = op
        display = "0"
    }

    fun calculate() {
        val secondOperand = display.toDoubleOrNull()
        if (firstOperand != null && secondOperand != null && currentOperation != null) {
            val result = when (currentOperation) {
                "+" -> firstOperand!! + secondOperand
                "-" -> firstOperand!! - secondOperand
                "×" -> firstOperand!! * secondOperand
                "÷" -> firstOperand!! / secondOperand
                else -> secondOperand
            }
            display = result.toString().take(10)
            firstOperand = null
            currentOperation = null
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFAFAFAF)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            CalculatorDisplay(display)
            Spacer(modifier = Modifier.height(16.dp))
            CalculatorKeypad(
                modifier = Modifier.weight(1f),
                onDigitClick = ::updateDisplay,
                onOperationClick = ::setOperation,
                onEqualsClick = ::calculate,
                onClearClick = { display = "0" },
                onMemoryRecall = { display = memory.toString() },
                onMemoryAdd = { memory += display.toDoubleOrNull() ?: 0.0 },
                onMemorySubtract = { memory -= display.toDoubleOrNull() ?: 0.0 },
                onSquareRoot = { display = Math.sqrt(display.toDoubleOrNull() ?: 0.0).toString() },
                onPercentage = { display = (display.toDoubleOrNull()?.div(100) ?: 0.0).toString() },
                onSignChange = { display = (-(display.toDoubleOrNull() ?: 0.0)).toString() }
            )
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
            color = Color.Black
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
            CalculatorButton("MRC", Modifier.weight(1f), onClick = onMemoryRecall)
            CalculatorButton("M-", Modifier.weight(1f), onClick = onMemorySubtract)
            CalculatorButton("M+", Modifier.weight(1f), onClick = onMemoryAdd)
            CalculatorButton("ON/C", Modifier.weight(1f), onClick = onClearClick, backgroundColor = Color(0xFFD1546F))
        }
        CalculatorRow {
            CalculatorButton("√", Modifier.weight(1f), onClick = onSquareRoot)
            CalculatorButton("%", Modifier.weight(1f), onClick = onPercentage)
            CalculatorButton("±", Modifier.weight(1f), onClick = onSignChange)
            CalculatorButton("CE", Modifier.weight(1f), onClick = onClearClick, backgroundColor = Color(0xFFD1546F))
        }
        CalculatorRow {
            CalculatorButton("7", Modifier.weight(1f), onClick = { onDigitClick("7") })
            CalculatorButton("8", Modifier.weight(1f), onClick = { onDigitClick("8") })
            CalculatorButton("8", Modifier.weight(1f), onClick = { onDigitClick("9") })
            CalculatorButton("÷", Modifier.weight(1f), onClick = { onOperationClick("÷") })
        }
        CalculatorRow {
            CalculatorButton("4", Modifier.weight(1f), onClick = { onDigitClick("4") })
            CalculatorButton("5", Modifier.weight(1f), onClick = { onDigitClick("5") })
            CalculatorButton("6", Modifier.weight(1f), onClick = { onDigitClick("6") })
            CalculatorButton("×", Modifier.weight(1f), onClick = { onOperationClick("×") })
        }
        CalculatorRow {
            CalculatorButton("1", Modifier.weight(1f), onClick = { onDigitClick("1") })
            CalculatorButton("2", Modifier.weight(1f), onClick = { onDigitClick("2") })
            CalculatorButton("3", Modifier.weight(1f), onClick = { onDigitClick("3") })
            CalculatorButton("-", Modifier.weight(1f), onClick = { onOperationClick("-") })
        }
        CalculatorRow {
            CalculatorButton("0", Modifier.weight(1f), onClick = { onDigitClick("0") })
            CalculatorButton(".", Modifier.weight(1f), onClick = { onDigitClick(".") })
            CalculatorButton("3", Modifier.weight(1f), onClick = onEqualsClick)
            CalculatorButton("+", Modifier.weight(1f), onClick = { onOperationClick("+") })
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
                maxLines = 1,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatorProjectTheme {
        CalculatorScreen()
    }
}
