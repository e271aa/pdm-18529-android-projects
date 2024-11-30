package com.example.newsapp.presentation.news_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.usecase.GetTopNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsListViewModel(private val getTopNewsUseCase: GetTopNewsUseCase) : ViewModel() {
    private val _news = MutableStateFlow<List<News>>(emptyList())
    val news: StateFlow<List<News>> = _news

    init {
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch {
            try {
                _news.value = getTopNewsUseCase()
            } catch (e: Exception) {
                // Tratar erro
            }
        }
    }
}