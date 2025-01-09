package com.example.carrinhopartilhado.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carrinhopartilhado.data.models.Article
import com.example.carrinhopartilhado.ui.components.ArticleCard
import com.example.carrinhopartilhado.viewmodel.CartViewModel

@Composable
fun ArticleListScreen(
    cartViewModel: CartViewModel = viewModel(),
    onArticleClick: (Article) -> Unit
) {
    var articles by remember { mutableStateOf(emptyList<Article>()) }

    LaunchedEffect(Unit) {
        cartViewModel.loadArticles { /* Handle errors */ }
        articles = cartViewModel.articles
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(articles.size) { index ->
            ArticleCard(article = articles[index]) {
                cartViewModel.addToCart(it) { /* Handle errors */ }
            }
        }
    }
}