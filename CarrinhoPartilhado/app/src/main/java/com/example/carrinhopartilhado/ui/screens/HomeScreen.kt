package com.example.carrinhopartilhado.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.carrinhopartilhado.ui.components.ArticleCard
import com.example.carrinhopartilhado.viewmodel.AuthViewModel
import com.example.carrinhopartilhado.viewmodel.CartViewModel
import com.example.carrinhopartilhado.viewmodel.CartStatus

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {}