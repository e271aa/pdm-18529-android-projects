package com.example.newsapp.data.remote.dto

data class NewsResponseDto(
    val meta: MetaDto,
    val data: List<NewsDto>
)

data class MetaDto(
    val found: Int,
    val returned: Int,
    val limit: Int,
    val page: Int
)
