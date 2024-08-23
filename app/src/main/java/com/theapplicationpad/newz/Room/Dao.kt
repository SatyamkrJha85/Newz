package com.theapplicationpad.newz.Room


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<ArticleEntity>>

    @Query("DELETE FROM articles")
    suspend fun clearAllArticles()

    @Query("UPDATE articles SET isLiked = :isLiked WHERE url = :url")
    suspend fun updateLikeStatus(url: String, isLiked: Boolean)

    @Query("SELECT * FROM articles WHERE url = :url")
    fun getArticleByUrl(url: String?): ArticleEntity?


}
