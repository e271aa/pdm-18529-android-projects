package com.example.carrinhopartilhado.data.repositories

import com.example.carrinhopartilhado.data.models.Article
import com.example.carrinhopartilhado.data.models.CartItem
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepository {
    private val db = FirebaseFirestore.getInstance()

    fun getArticles(onSuccess: (List<Article>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("articles")
            .get()
            .addOnSuccessListener { result ->
                val articles = result.map { it.toObject(Article::class.java) }
                onSuccess(articles)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    fun addToCart(cartItem: CartItem, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("cart").add(cartItem)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }
}