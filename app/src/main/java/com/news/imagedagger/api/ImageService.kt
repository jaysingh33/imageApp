package com.news.imagedagger.api

import com.news.imagedagger.model.ImageData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService  {

    @GET("list")
    suspend fun getImages(@Query("page") page: Int, @Query("limit") limit: Int): Response<ImageData>
}