package com.example.carrinhopartilhado.data.models

data class CartItem(
    val article: Article,
    val quantity: Int = 1
)