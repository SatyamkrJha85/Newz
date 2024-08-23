package com.theapplicationpad.newz.Retrofit

import com.theapplicationpad.newz.Model.NewsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewzApiService {
    @GET("top-headlines")
    suspend fun getNewsArticles(
        @Query("category")category:String,
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey:String= ApiKey
    ): Response<NewsDTO>

    @GET("everything")
    suspend fun getNewsbysearch(
        @Query("q") query: String,
        @Query("apiKey") apiKey:String= ApiKey
    ): Response<NewsDTO>


    companion object{
        // first key f2a4512e95bf44209866e0ed4e11c495
        //second key 13ca26ef1be44f9da3a7756f328c91d1
        const val ApiKey = "13ca26ef1be44f9da3a7756f328c91d1"
    }
}




