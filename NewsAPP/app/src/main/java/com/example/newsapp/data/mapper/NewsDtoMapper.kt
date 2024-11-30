package com.example.newsapp.data.mapper

import com.example.newsapp.data.remote.dto.NewsDto
import com.example.newsapp.domain.model.News

class NewsDtoMapper {
    fun mapToDomain(dto: NewsDto): News {
        return News(
            id = dto.uuid,
            title = dto.title,
            description = dto.description,
            imageUrl = dto.image_url,
            publishedAt = dto.published_at
        )
    }
}