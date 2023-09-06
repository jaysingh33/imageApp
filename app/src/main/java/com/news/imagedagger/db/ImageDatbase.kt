package com.news.imagedagger.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.news.imagedagger.model.ImageData
import com.news.imagedagger.model.ImageDataItem


@Database(entities = [ImageDataItem::class], version = 1)
abstract class ImageDatbase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
  
    companion object {
        @Volatile
        private var INSTANCE: ImageDatbase? = null

        fun getDatabase(context: Context): ImageDatbase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        ImageDatbase::class.java, "imageDB"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}