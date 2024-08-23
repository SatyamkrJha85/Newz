package com.theapplicationpad.newz.Model

data class NewsDTO(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)