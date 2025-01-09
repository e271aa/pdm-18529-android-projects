package com.example.carrinhopartilhado.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.carrinhopartilhado.data.models.Article

@Composable
fun ArticleCard(article: Article, onAddToCart: (Article) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // Substituindo pelo AsyncImage do Coil Compose
            AsyncImage(
                model = article.imageUrl,
                contentDescription = article.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(article.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("€${article.price}", fontSize = 14.sp)
            }

            Button(onClick = { onAddToCart(article) }) {
                Text("Adicionar")
            }
        }
    }
}