package com.news.imagedagger.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.news.imagedagger.api.ImageService
import com.news.imagedagger.db.ImageDatbase
import com.news.imagedagger.model.ImageDataItem
import com.news.imagedagger.utils.NetworkUtils

class ImageRepository(
    private val imageService: ImageService,
    val imageDatbase: ImageDatbase,
    private val applicationContext: Context
) {


val images: LiveData<List<ImageDataItem>> = imageDatbase.imageDao().getImage()


    val localImages: LiveData<List<ImageDataItem>> = imageDatbase.imageDao().getImage()


    suspend fun getImage(page: Int, limit: Int) {
        if (NetworkUtils.internetAvailable(applicationContext)) {
            val result = imageService.getImages(page, limit)
            if (result.isSuccessful && result.body() != null) {
                imageDatbase.imageDao().addImage(result.body()!!)
            }
        }
    }
}