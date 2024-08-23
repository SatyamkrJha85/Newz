package com.theapplicationpad.newz.Room


import com.theapplicationpad.newz.Retrofit.NewzApiService
import com.theapplicationpad.newz.Retrofit.NewsResponse

import com.theapplicationpad.newz.Model.NewsDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: NewzApiService,
    private val articleDao: ArticleDao
) {

    suspend fun fetchNewsAndSaveToDB(catergory:String): Flow<NewsResponse<Unit>> = flow {
        emit(NewsResponse.loading)
        try {
            val response = apiService.getNewsArticles(catergory)
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let { newsDTO ->
                    val articles = newsDTO.articles.map { article ->
                        ArticleEntity(
                            url = article.url,
                            author = article.author,
                            content = article.content,
                            description = article.description,
                            publishedAt = article.publishedAt,
                            sourceName = article.source.name,
                            title = article.title,
                            urlToImage = article.urlToImage
                        )
                    }
                    articleDao.clearAllArticles()
                    articleDao.insertArticles(articles)
                    emit(NewsResponse.Success(Unit))
                }
            } else {
                emit(NewsResponse.Errors("Failed to fetch data"))
            }
        } catch (e: Exception) {
            emit(NewsResponse.Errors(e.message ?: "Unknown Error"))
        }
    }




    // New function to fetch news by search query and save to DB
    suspend fun fetchNewsBySearchAndSaveToDB(query: String): Flow<NewsResponse<Unit>> = flow {
        emit(NewsResponse.loading)
        try {
            val response = apiService.getNewsbysearch(query)
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let { newsDTO ->
                    saveArticlesToDB(newsDTO)
                    emit(NewsResponse.Success(Unit))
                }
            } else {
                emit(NewsResponse.Errors("Failed to fetch data"))
            }
        } catch (e: Exception) {
            emit(NewsResponse.Errors(e.message ?: "Unknown Error"))
        }
    }

    // Reusable function to save articles to DB
    private suspend fun saveArticlesToDB(newsDTO: NewsDTO) {
        val articles = newsDTO.articles.map { article ->
            ArticleEntity(
                url = article.url,
                author = article.author,
                content = article.content,
                description = article.description,
                publishedAt = article.publishedAt,
                sourceName = article.source.name,
                title = article.title,
                urlToImage = article.urlToImage
            )
        }
        articleDao.clearAllArticles()
        articleDao.insertArticles(articles)
    }

    fun getArticleByUrl(url: String?): ArticleEntity? {
        return articleDao.getArticleByUrl(url)
    }


    // Existing function to get saved articles from the DB
    fun getSavedArticles(): Flow<List<ArticleEntity>> {
        return articleDao.getAllArticles()
    }
}
