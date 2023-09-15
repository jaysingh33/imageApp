package com.news.imagedagger.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.news.imagedagger.model.ImageDataItem


@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ImageDataItem>)

    @Query("SELECT * FROM image")
    fun getAllItems(): PagingSource<Int, ImageDataItem>

//    @Query("SELECT * FROM image")
//    fun getImageLiveData(): LiveData<List<ImageDataItem>>

    // Other DAO methods...

    @Query("SELECT * FROM image LIMIT :pageSize OFFSET :offset")
    fun getImageWithPagination(pageSize: Int, offset: Int): LiveData<List<ImageDataItem>>

    @Query("DELETE FROM image")
    suspend fun clearAll()
}
