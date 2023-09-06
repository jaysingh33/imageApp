package com.news.imagedagger.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.news.imagedagger.model.ImageDataItem

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)  // or OnConflictStrategy.IGNORE
    suspend fun addImage(image: List<ImageDataItem>)

    @Query("SELECT * FROM image")
     fun getImage():  LiveData<List<ImageDataItem>>// List<ImageDataItem>

    @Query("SELECT * FROM image LIMIT :pageSize OFFSET :offset")
    fun getImageWithPagination(pageSize: Int, offset: Int): LiveData<List<ImageDataItem>>
}