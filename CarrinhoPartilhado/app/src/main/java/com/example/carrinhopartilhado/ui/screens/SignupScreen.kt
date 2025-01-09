package com.example.carrinhopartilhado.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.carrinhopartilhado.viewmodel.AuthState
import com.example.carrinhopartilhado.viewmodel.AuthViewModel

/*
@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Signup Page", fontSize = 32.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = "Email")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Password")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                authViewModel.signup(email, password)
            }, enabled = authState.value != AuthState.Loading
        ) {
            Text(text = "Create account")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = {
            navController.navigate("login")
        }) {
            Text(text = "Already have an account, Login")
        }

    }
}*/

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Estado de validação do formulário
    val isFormValid by remember {
        derivedStateOf { email.isNotBlank() && password.isNotBlank() }
    }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message,
                Toast.LENGTH_SHORT
            ).show()
            else -> Unit
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp), // Adiciona espaçamento lateral para melhorar o layout
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título com maior espaço no topo
        Text(
            text = "Cria a tua conta",
            fontSize = 32.sp,
            modifier = Modifier.padding(top = 80.dp) // Aumenta o espaço do topo
        )

        Spacer(modifier = Modifier.height(32.dp)) // Espaço maior entre o título e os campos

        // Campo de Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Endereço de e-mail") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp)) // Espaçamento entre os campos

        // Campo de Palavra-passe
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Palavra-passe") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Texto de login de conta existente
        TextButton(
            onClick = { navController.navigate("login") },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Já tens uma conta? Inicia sessão aqui")
        }

        // Spacer que empurra o botão para baixo, mas mantendo um bom espaçamento
        Spacer(modifier = Modifier.height(32.dp)) // Ajusta para o botão não ficar tão colado ao fundo

        // Botão de Criar Conta com mais espaço entre o botão e o fundo
        Button(
            onClick = { authViewModel.signup(email, password) },
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFormValid) Color(0xFF4CAF50) else Color(0xFFA5D6A7), // Verde saturado ou pastel
                disabledContainerColor = Color(0xFFA5D6A7), // Cor desativada
                contentColor = Color.White // Garante que o texto seja branco
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp) // Define uma altura maior para o botão
                .padding(horizontal = 16.dp) // Ajusta o padding
        ) {
            Text(
                text = "Criar Conta",
                fontSize = 18.sp,
                color = Color.White // Garante que o texto seja visível
            )
        }
    }
}
