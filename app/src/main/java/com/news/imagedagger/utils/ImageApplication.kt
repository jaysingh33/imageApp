package com.news.imagedagger.utils

import android.app.Application
import com.news.imagedagger.api.ImageService
import com.news.imagedagger.api.RetrofitHelper
import com.news.imagedagger.db.ImageDatbase
import com.news.imagedagger.repository.ImageRepository



class ImageApplication : Application() {
    lateinit var imageRepository: ImageRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }
    private fun initialize() {
        val imageService = RetrofitHelper.getInstance().create(ImageService::class.java)
        val database = ImageDatbase.getDatabase(applicationContext) // Assuming ImageDatabase is your Room database class
        imageRepository = ImageRepository(imageService, database, applicationContext)
    }
}
