package com.example.carrinhopartilhado.viewmodel

sealed class CartStatus {
    object Loading : CartStatus()  // O carrinho está sendo carregado
    object Success : CartStatus()  // O artigo foi adicionado com sucesso
    object Error : CartStatus()    // Erro ao adicionar um artigo
}