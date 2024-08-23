package com.theapplicationpad.newz.Retrofit

sealed class NewsResponse<out T> {
    data class Success <out T>(val data: T):NewsResponse<T>()
    data class Errors(val message:String):NewsResponse<Nothing>()
    object loading:NewsResponse<Nothing>()
}