package com.example.calculator_07102024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.calculator_07102024.ui.theme.Calculator_07102024Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculator_07102024Theme {
                Calculator()
                /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }*/
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun CalculatorButton(text: String, onClick: () -> Unit){
    Button(
        onClick = onClick,
    ) {
        Text(text = text, fontSize = 24.sp)
    }
}

@Composable
fun Calculator(){
    Column {
        Row {
            CalculatorButton("7") { }
            CalculatorButton("8") { }
            CalculatorButton("9") { }
            CalculatorButton("/") { }
        }
        Row {
            CalculatorButton("4") { }
            CalculatorButton("5") { }
            CalculatorButton("6") { }
            CalculatorButton("*") { }
        }
        Row {
            CalculatorButton("1") { }
            CalculatorButton("2") { }
            CalculatorButton("3") { }
            CalculatorButton("-") { }
        }
        Row {
            CalculatorButton("0") { }
            CalculatorButton(".") { }
            CalculatorButton("=") { }
            CalculatorButton("+") { }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Calculator_07102024Theme {
        // Greeting("Android")
        Calculator()
    }
}