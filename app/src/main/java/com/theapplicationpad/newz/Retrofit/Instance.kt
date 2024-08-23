package com.theapplicationpad.newz.Retrofit


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsInstance {
    private const val Baseurl="https://newsapi.org/v2/"

    private fun getinstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(Baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val newsapi:NewzApiService= getinstance().create(NewzApiService::class.java)
}