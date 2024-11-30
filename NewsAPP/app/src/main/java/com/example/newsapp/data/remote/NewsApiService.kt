package com.example.newsapp.data.remote

import com.example.newsapp.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v1/news/top")
    suspend fun getTopNews(
        @Query("api_token") apiToken: String = "gMObFQ8yPN1MWG2jYVMa6m9Dpooi3ffkNtNEh8b8",
        @Query("locale") locale: String = "pt",
        @Query("limit") limit: Int = 10
    ): NewsResponseDto
}