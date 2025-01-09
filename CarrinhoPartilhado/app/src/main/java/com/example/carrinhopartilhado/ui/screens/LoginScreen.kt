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
fun LoginScreen(modifier: Modifier = Modifier,navController: NavController,authViewModel: AuthViewModel) {


    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login Page", fontSize = 32.sp)

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

        Button(onClick = {
            authViewModel.login(email,password)
        },
            enabled = authState.value != AuthState.Loading
        ) {
            Text(text = "Login")
        }


        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = {
            navController.navigate("signup")
        }) {
            Text(text = "Don't have an account, Signup")
        }

    }

}*/

/*@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) } // Controle de visibilidade da senha

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
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Hey there, welcome back",
                fontSize = 28.sp,
                color = Color(0xFF3A3A3A) // Cinza escuro
            )
            Spacer(modifier = Modifier.height(32.dp))
        }

        // Campos de entrada
        Column(verticalArrangement = Arrangement.Center) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    val icon = if (passwordVisible) {
                        painterResource(id = R.drawable.ic_launcher_foreground) // Substitua pelo ícone correto
                    } else {
                        painterResource(id = R.drawable.ic_launcher_foreground) // Substitua pelo ícone correto
                    }
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(painter = icon, contentDescription = null)
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { navController.navigate("signup") }) {
                Text(
                    text = "Don't have an account yet? Register",
                    color = Color(0xFF6200EE) // Roxo escuro
                )
            }
        }

        // Botão de login
        Button(
            onClick = { authViewModel.login(email, password) },
            enabled = authState.value != AuthState.Loading,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp) // Botão grande
        ) {
            Text(text = "Login", fontSize = 18.sp)
        }

        // Indicador de carregamento
        if (authState.value == AuthState.Loading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        }
    }
}*/

/*@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) } // Controle de visibilidade da senha

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    // Verifica se os campos estão completos para ativar o botão
    val isFormValid = email.isNotBlank() && password.isNotBlank()

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
            .padding(24.dp),
        verticalArrangement = Arrangement.Top, // Alinhado ao topo
        horizontalAlignment = Alignment.Start // Alinhado à esquerda
    ) {
        // Título
        Text(
            text = "Hey there, welcome back",
            fontSize = 28.sp,
            color = Color(0xFF3A3A3A), // Cinza escuro
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Campos de entrada
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                val icon = if (passwordVisible) {
                    painterResource(id = R.drawable.ic_launcher_foreground) // Ícone visível
                } else {
                    painterResource(id = R.drawable.ic_launcher_foreground) // Ícone oculto
                }
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = icon, contentDescription = null)
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Texto de registro
        TextButton(onClick = { navController.navigate("signup") }) {
            Text(
                text = "Don't have an account yet? Register",
                color = Color(0xFF6200EE) // Roxo escuro
            )
        }

        Spacer(modifier = Modifier.weight(1f)) // Empurra o botão para baixo

        // Botão de login
        Button(
            onClick = { authViewModel.login(email, password) },
            enabled = isFormValid, // O botão só será habilitado quando ambos os campos estiverem preenchidos
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFormValid) Color(0xFF4CAF50) else Color(0xFFA5D6A7), // Verde saturado ou pastel
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp) // Botão grande
        ) {
            Text(text = "Login", fontSize = 18.sp)
        }

        // Indicador de carregamento (opcional)
        if (authState.value == AuthState.Loading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        }
    }
}*/

/*
@Composable
fun LoginScreen(
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
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hey there, Welcome back", fontSize = 32.sp, modifier = Modifier.padding(top = 48.dp))

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { navController.navigate("signup") },
            modifier = Modifier.align(Alignment.End).padding(end = 16.dp)
        ) {
            Text(text = "Don't have an account yet? Register")
        }

        Spacer(modifier = Modifier.weight(1f)) // Empurra o botão para o final da página

        Button(
            onClick = { authViewModel.login(email, password) },
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFormValid) Color(0xFF4CAF50) else Color(0xFFA5D6A7), // Verde saturado ou pastel
                disabledContainerColor = Color(0xFFA5D6A7), // Cor desativada
                contentColor = Color.White // Garante que o texto seja branco
            ),
            modifier = Modifier
                .fillMaxWidth() // Preenche a largura do container
                .height(56.dp) // Define uma altura padrão para o botão
                .padding(horizontal = 16.dp) // Espaçamento horizontal
        ) {
            Text(
                text = "Login",
                fontSize = 18.sp,
                color = Color.White // Garante que o texto seja visível
            )
        }
    }
}*/

/*
@Composable
fun LoginScreen(
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
        // Título
        Text(
            text = "Olá, bem-vindo de volta!",
            fontSize = 32.sp,
            modifier = Modifier.padding(top = 48.dp)
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

        // Texto de registo de nova conta
        TextButton(
            onClick = { navController.navigate("signup") },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Ainda não tens conta? Regista-te aqui")
        }

        Spacer(modifier = Modifier.weight(1f)) // Empurra o botão para o final da página

        // Botão de Login
        Button(
            onClick = { authViewModel.login(email, password) },
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFormValid) Color(0xFF4CAF50) else Color(0xFFA5D6A7), // Verde saturado ou pastel
                disabledContainerColor = Color(0xFFA5D6A7), // Cor desativada
                contentColor = Color.White // Garante que o texto seja branco
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp) // Define uma altura maior para o botão
        ) {
            Text(
                text = "Iniciar Sessão",
                fontSize = 18.sp,
                color = Color.White // Garante que o texto seja visível
            )
        }
    }
}
*/

@Composable
fun LoginScreen(
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
            text = "Olá, bem-vindo de volta!",
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

        // Texto de registo de nova conta
        TextButton(
            onClick = { navController.navigate("signup") },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Ainda não tens conta? Regista-te aqui")
        }

        // Spacer que empurra o botão para baixo, mas mantendo um bom espaçamento
        Spacer(modifier = Modifier.height(32.dp)) // Ajusta para o botão não ficar tão colado ao fundo

        // Botão de Login com mais espaço entre o botão e o fundo
        Button(
            onClick = { authViewModel.login(email, password) },
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
                text = "Iniciar Sessão",
                fontSize = 18.sp,
                color = Color.White // Garante que o texto seja visível
            )
        }
    }
}