package com.example.calculatorproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.calculatorproject.ui.CalculatorScreen
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