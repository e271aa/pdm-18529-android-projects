package com.example.newsapp.data.remote.dto

data class NewsDto(
    val uuid: String,
    val title: String,
    val description: String,
    val url: String,
    val image_url: String?,
    val published_at: String
)
