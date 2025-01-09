package com.example.carrinhopartilhado.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carrinhopartilhado.ui.screens.HomeScreen
import com.example.carrinhopartilhado.ui.screens.LoginScreen
import com.example.carrinhopartilhado.ui.screens.SignupScreen
import com.example.carrinhopartilhado.viewmodel.AuthViewModel
import com.example.carrinhopartilhado.viewmodel.CartViewModel

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier,authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login"){
            LoginScreen(modifier,navController,authViewModel)
        }
        composable("signup"){
            SignupScreen(modifier,navController,authViewModel)
        }
        composable("home"){
            HomeScreen(modifier,navController, authViewModel)
        }
    })
}