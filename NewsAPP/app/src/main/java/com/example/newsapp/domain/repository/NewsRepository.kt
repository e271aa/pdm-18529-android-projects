package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.News

interface NewsRepository {
    suspend fun getTopNews(): List<News>
}