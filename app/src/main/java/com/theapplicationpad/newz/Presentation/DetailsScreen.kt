package com.theapplicationpad.newz.Presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theapplicationpad.newz.Room.ArticleEntity

@Composable
fun DetailsScreen( modifier: Modifier = Modifier,
                   article: ArticleEntity // Pass the article as a parameter
) {
    // Display the details of the article
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = article.title ?: "No Title", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Text(text = "By: ${article.author ?: "Unknown"}", fontSize = 18.sp)
        Text(text = article.description ?: "No Description", fontSize = 16.sp)
        Text(text = "Published at: ${article.publishedAt ?: "Unknown"}", fontSize = 14.sp)
        Text(text = "Source: ${article.sourceName ?: "Unknown"}", fontSize = 14.sp)
    }
}