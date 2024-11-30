package com.example.newsapp.presentation.news_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapp.domain.model.News
import com.example.newsapp.presentation.news_list.NewsListViewModel

@Composable
fun NewsListScreen(viewModel: NewsListViewModel, onNewsClick: (String) -> Unit) {
    val news = viewModel.news.collectAsState().value

    LazyColumn {
        items(news) { newsItem ->
            NewsItem(news = newsItem, onClick = { onNewsClick(newsItem.id) })
        }
    }
}

@Composable
fun NewsItem(news: News, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column {
            AsyncImage(
                model = news.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(text = news.title, style = MaterialTheme.typography.h6)
            Text(text = news.description, maxLines = 2)
        }
    }
}