package com.theapplicationpad.newz


import android.content.Context
import androidx.room.Room
import com.theapplicationpad.newz.Retrofit.NewzApiService
import com.theapplicationpad.newz.Room.ArticleDao
import com.theapplicationpad.newz.Room.NewsDatabase
import com.theapplicationpad.newz.Room.NewsRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApiService(): NewzApiService {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewzApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_db"
        ).build()
    }

    @Provides
    fun provideArticleDao(database: NewsDatabase): ArticleDao {
        return database.articleDao()
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        apiService: NewzApiService,
        articleDao: ArticleDao
    ): NewsRepository {
        return NewsRepository(apiService, articleDao)
    }
}
