package com.example.carrinhopartilhado.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrinhopartilhado.data.models.Article
import com.example.carrinhopartilhado.data.models.CartItem
import com.example.carrinhopartilhado.data.repositories.FirebaseRepository
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    var articles: List<Article> = emptyList()
        private set

    fun loadArticles(onFailure: (Exception) -> Unit) {
        repository.getArticles(
            onSuccess = { articles = it },
            onFailure = { onFailure(it) }
        )
    }

    fun addToCart(article: Article, onFailure: (Exception) -> Unit) {
        val cartItem = CartItem(article)
        viewModelScope.launch {
            repository.addToCart(cartItem, onSuccess = {}, onFailure = { onFailure(it) })
        }
    }
}