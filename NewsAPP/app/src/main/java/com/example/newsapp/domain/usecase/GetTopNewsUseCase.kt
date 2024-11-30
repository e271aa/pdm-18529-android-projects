package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.repository.NewsRepository

class GetTopNewsUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(): List<News> = repository.getTopNews()
}