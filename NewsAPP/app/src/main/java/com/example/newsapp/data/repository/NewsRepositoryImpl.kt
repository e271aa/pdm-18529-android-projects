package com.example.newsapp.data.repository

import com.example.newsapp.data.mapper.NewsDtoMapper
import com.example.newsapp.data.remote.NewsApiService
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val api: NewsApiService,
    private val mapper: NewsDtoMapper
) : NewsRepository {
    override suspend fun getTopNews(): List<News> {
        return api.getTopNews().data.map { mapper.mapToDomain(it) }
    }
}