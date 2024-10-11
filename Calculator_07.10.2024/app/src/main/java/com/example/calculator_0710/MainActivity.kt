/*
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
    // Estado que guarda o valor mostrado no visor
    val display = remember { mutableStateOf("0") }

    // Estados que guardam o primeiro número e a operação selecionada
    val firstNumber = remember { mutableStateOf("") }
    val operation = remember { mutableStateOf<Char?>(null) }

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
            // Corrigindo aqui: Pegamos o valor de 'display' com '.value'
            Text(
                text = display.value,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 48.sp // Tamanho maior para o display
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalculatorRow(listOf("7", "8", "9", "/")) { label ->
                onButtonClick(label, display.value, { display.value = it }, { firstNumber.value = it }, { operation.value = it })
            }
            CalculatorRow(listOf("4", "5", "6", "*")) { label ->
                onButtonClick(label, display.value, { display.value = it }, { firstNumber.value = it }, { operation.value = it })
            }
            CalculatorRow(listOf("1", "2", "3", "-")) { label ->
                onButtonClick(label, display.value, { display.value = it }, { firstNumber.value = it }, { operation.value = it })
            }
            CalculatorRow(listOf("0", ".", "=", "+")) { label ->
                onButtonClick(label, display.value, { display.value = it }, { firstNumber.value = it }, { operation.value = it })
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

// Função para tratar os cliques nos botões
fun onButtonClick(
    label: String,
    currentDisplay: String,
    updateDisplay: (String) -> Unit,
    updateFirstNumber: (String) -> Unit,
    updateOperation: (Char?) -> Unit
) {
    when (label) {
        in "0".."9" -> {
            // Limita o visor a 9 dígitos e não permite múltiplos zeros à esquerda
            if (currentDisplay.length < 9) {
                val newDisplay = if (currentDisplay == "0") label else currentDisplay + label
                updateDisplay(newDisplay)
            }
        }
        ".", "=" -> {
            if (label == "=") {
                // Avalia a expressão quando o botão igual é pressionado
                try {
                    val result = evaluateExpression(currentDisplay)
                    updateDisplay(result)
                } catch (e: Exception) {
                    updateDisplay("Erro")
                }
            } else if (label == ".") {
                // Adiciona ponto decimal
                if (!currentDisplay.contains(".")) {
                    updateDisplay(currentDisplay + ".")
                }
            }
        }
        "+", "-", "*", "/" -> {
            // Guarda o primeiro número e a operação selecionada
            updateFirstNumber(currentDisplay)
            updateOperation(label[0])
            updateDisplay("0") // Limpa o display para o próximo número
        }
    }
}

// Função simples para avaliar a expressão (somente para soma no exemplo)
fun evaluateExpression(expression: String): String {
    // Aqui você pode usar lógica mais avançada para lidar com diferentes operações
    // Para simplificação, só suporta soma
    return expression // Isso pode ser substituído pela lógica adequada
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Calculator_0710Theme {
        Calculator()
    }
}*/

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
import kotlin.math.round
import androidx.compose.ui.text.style.TextAlign
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
    // Estado que guarda o valor mostrado no visor
    var display = remember { mutableStateOf("0") }

    // Estados que guardam o primeiro número e a operação selecionada
    var firstNumber = remember { mutableStateOf("") }
    var operation = remember { mutableStateOf<Char?>(null) }

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
            // Corrigindo aqui: Pegamos o valor de 'display' com '.value'
            Text(
                text = display.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp), // Ajusta o padding vertical para aproximar mais dos botões
                fontSize = 48.sp, // Tamanho grande para o visor
                textAlign = TextAlign.Right // Alinha o texto à direita
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalculatorRow(listOf("7", "8", "9", "/")) { label ->
                onButtonClick(label, display.value, { display.value = it }, { firstNumber.value = it }, { operation.value = it }, firstNumber.value, operation.value)
            }
            CalculatorRow(listOf("4", "5", "6", "*")) { label ->
                onButtonClick(label, display.value, { display.value = it }, { firstNumber.value = it }, { operation.value = it }, firstNumber.value, operation.value)
            }
            CalculatorRow(listOf("1", "2", "3", "-")) { label ->
                onButtonClick(label, display.value, { display.value = it }, { firstNumber.value = it }, { operation.value = it }, firstNumber.value, operation.value)
            }
            CalculatorRow(listOf("0", ".", "=", "+")) { label ->
                onButtonClick(label, display.value, { display.value = it }, { firstNumber.value = it }, { operation.value = it }, firstNumber.value, operation.value)
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

// Função para tratar os cliques nos botões
fun onButtonClick(
    label: String,
    currentDisplay: String,
    updateDisplay: (String) -> Unit,
    updateFirstNumber: (String) -> Unit,
    updateOperation: (Char?) -> Unit,
    firstNumber: String,
    operation: Char?
) {
    when (label) {
        in "0".."9" -> {
            // Limita o visor a 9 dígitos e não permite múltiplos zeros à esquerda
            if (currentDisplay.length < 9) {
                val newDisplay = if (currentDisplay == "0") label else currentDisplay + label
                updateDisplay(newDisplay)
            }
        }
        ".", "=" -> {
            if (label == "=") {
                // Avalia a expressão quando o botão igual é pressionado
                if (firstNumber.isNotEmpty() && operation != null) {
                    try {
                        val result = evaluateExpression(firstNumber, currentDisplay, operation)
                        updateDisplay(result)
                    } catch (e: Exception) {
                        updateDisplay("Erro")
                    }
                }
            } else if (label == ".") {
                // Adiciona ponto decimal
                if (!currentDisplay.contains(".")) {
                    updateDisplay(currentDisplay + ".")
                }
            }
        }
        "+", "-", "*", "/" -> {
            // Guarda o primeiro número e a operação selecionada
            updateFirstNumber(currentDisplay)
            updateOperation(label[0])
            updateDisplay("0") // Limpa o display para o próximo número
        }
    }
}

// Função para avaliar a expressão baseada em dois números e a operação
fun evaluateExpression(firstNumber: String, secondNumber: String, operation: Char?): String {
    val num1 = firstNumber.toDoubleOrNull() ?: return "Erro"
    val num2 = secondNumber.toDoubleOrNull() ?: return "Erro"

    val result = when (operation) {
        '+' -> num1 + num2
        '-' -> num1 - num2
        '*' -> num1 * num2
        '/' -> if (num2 != 0.0) num1 / num2 else return "Erro"
        else -> return "Erro"
    }

    // Verifica se o resultado é um número inteiro
    return if (result % 1.0 == 0.0) {
        // Se for inteiro, remove as casas decimais
        result.toInt().toString()
    } else {
        // Se não for inteiro, limita a 9 dígitos com arredondamento
        formatDecimal(result, 9)
    }
}

// Função para formatar números decimais com limite de dígitos e arredondamento
fun formatDecimal(value: Double, maxDigits: Int): String {
    // Verifica quantos dígitos a parte inteira tem
    val integerPartLength = value.toLong().toString().length

    // Limita o número de dígitos decimais baseando-se no total de 9 dígitos
    val decimalDigits = maxDigits - integerPartLength

    // Formata o número com a quantidade adequada de casas decimais
    return if (decimalDigits > 0) {
        // Limita as casas decimais e faz o arredondamento
        "%.${decimalDigits}f".format(value).trimEnd('0').trimEnd('.') // Remove zeros e ponto decimal desnecessários
    } else {
        // Se a parte inteira já tem 9 ou mais dígitos, retorna só a parte inteira
        value.toLong().toString()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Calculator_0710Theme {
        Calculator()
    }
}
