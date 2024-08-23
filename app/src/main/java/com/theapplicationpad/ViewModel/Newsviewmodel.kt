package com.theapplicationpad.ViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theapplicationpad.newz.Retrofit.NewsResponse
import com.theapplicationpad.newz.Room.ArticleEntity
import com.theapplicationpad.newz.Room.NewsRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _articles = MutableStateFlow<List<ArticleEntity>>(emptyList())
    val articles: StateFlow<List<ArticleEntity>> = _articles

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

//    init {
//        fetchNews()
//    }

    // Existing properties and methods...

    fun fetchNews(category: String) {
        viewModelScope.launch {
            repository.fetchNewsAndSaveToDB(category).collectLatest { response ->
                handleResponse(response)
            }
        }
        loadSavedArticles()
    }

    fun getArticleByUrl(url: String?): ArticleEntity? {
        return repository.getArticleByUrl(url)
    }


    // New method to fetch news by search query
    fun searchNews(query: String) {
        viewModelScope.launch {
            repository.fetchNewsBySearchAndSaveToDB(query).collectLatest { response ->
                handleResponse(response)
            }
        }
        loadSavedArticles()
    }

    private fun handleResponse(response: NewsResponse<Unit>) {
        when (response) {
            is NewsResponse.Success -> {
                _loading.value = false
                _error.value = null
            }
            is NewsResponse.Errors -> {
                _loading.value = false
                _error.value = response.message
            }
            is NewsResponse.loading -> {
                _loading.value = true
            }
        }
    }

    private fun loadSavedArticles() {
        viewModelScope.launch {
            repository.getSavedArticles().collectLatest {
                _articles.value = it
            }
        }
    }




}
