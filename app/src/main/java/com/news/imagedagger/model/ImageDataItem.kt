package com.news.imagedagger.model

import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
data class  ImageDataItem(
   @PrimaryKey(autoGenerate = true)
    val imageId:Int,
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)