package com.example.newsapp.domain.model

data class News(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String?,
    val publishedAt: String
)