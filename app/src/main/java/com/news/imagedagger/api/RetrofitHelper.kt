package com.news.imagedagger.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val BASE_URL="https://picsum.photos/v2/"

    fun getInstance() : Retrofit {
           return Retrofit.Builder( )
               .baseUrl(BASE_URL)
               . addConverterFactory(GsonConverterFactory.create())
               .build()
    }
}