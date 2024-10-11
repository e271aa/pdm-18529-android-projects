package com.example.calculator_0710

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator_0710.ui.theme.Calculator_0710Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculator_0710Theme {
                Calculator()
            }
        }
    }
}

@Composable
fun Calculator() {
    // Declarando estados dentro de uma função composable
    val inputText = remember { mutableStateOf("") }  // Guarda o valor da entrada
    val resultText = remember { mutableStateOf("0") } // Guarda o valor do resultado

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = inputText.value, // Exibe a entrada atual
                modifier = Modifier.fillMaxWidth(),
                fontSize = 32.sp
            )
            Text(
                text = resultText.value, // Exibe o resultado
                modifier = Modifier.fillMaxWidth(),
                fontSize = 40.sp
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalculatorRow(listOf("7", "8", "9", "/")) { label ->
                onButtonClick(label, inputText, resultText)
            }
            CalculatorRow(listOf("4", "5", "6", "*")) { label ->
                onButtonClick(label, inputText, resultText)
            }
            CalculatorRow(listOf("1", "2", "3", "-")) { label ->
                onButtonClick(label, inputText, resultText)
            }
            CalculatorRow(listOf("0", ".", "=", "+")) { label ->
                onButtonClick(label, inputText, resultText)
            }
        }
    }
}

@Composable
fun CalculatorButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(4.dp)
            .height(80.dp)
    ) {
        Text(text = text, fontSize = 24.sp)
    }
}

@Composable
fun CalculatorRow(buttons: List<String>, onButtonClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        buttons.forEach { label ->
            CalculatorButton(label, Modifier.weight(1f), onClick = { onButtonClick(label) })
        }
    }
}

// Função para tratar quando um botão é clicado
fun onButtonClick(
    label: String,
    inputText: androidx.compose.runtime.MutableState<String>,
    resultText: androidx.compose.runtime.MutableState<String>
) {
    when (label) {
        "=" -> {
            try {
                // Avalia a expressão inserida pelo usuário
                val result = evaluateExpression(inputText.value)
                resultText.value = result.toString() // Atualiza o resultado
            } catch (e: Exception) {
                resultText.value = "Erro" // Em caso de erro
            }
        }

        "+" -> inputText.value += "+"
        "-" -> inputText.value += "-"
        "*" -> inputText.value += "*"
        "/" -> inputText.value += "/"
        "." -> inputText.value += "."
        else -> inputText.value += label // Para números
    }
}

fun evaluateExpression(expression: String): Double {
    // Você pode usar uma função mais sofisticada aqui para avaliar expressões completas.
    return try {
        expression.toDouble() // Simples para um número
    } catch (e: NumberFormatException) {
        0.0 // Retorna 0 se der erro
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Calculator_0710Theme {
        Calculator()
    }
}