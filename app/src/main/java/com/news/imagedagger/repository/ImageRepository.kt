package com.news.imagedagger.repository

import android.content.Context
import androidx.paging.*
import com.news.imagedagger.api.ImageService
import com.news.imagedagger.db.ImageDao
import com.news.imagedagger.db.ImageDatbase
import com.news.imagedagger.model.ImageDataItem
import com.news.imagedagger.paging.ImageRemoteMediator
import kotlinx.coroutines.flow.Flow

class ImageRepository(
    private val imageService: ImageService,
    private val imageDatabase: ImageDatbase,
    private val applicationContext: Context
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getImages(): Flow<PagingData<ImageDataItem>> {
        return Pager(
            config = PagingConfig(pageSize = 15),
            remoteMediator = ImageRemoteMediator(imageService, imageDatabase, applicationContext),
            pagingSourceFactory = { imageDatabase.imageDao().getAllItems() }
        ).flow
    }
}




