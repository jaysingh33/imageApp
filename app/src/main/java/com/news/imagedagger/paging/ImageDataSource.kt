package com.news.imagedagger.paging

import androidx.paging.PageKeyedDataSource
import com.news.imagedagger.db.ImageDao
import com.news.imagedagger.model.ImageDataItem

class ImageDataSource(private val imageDao: ImageDao) :
    PageKeyedDataSource<Int, ImageDataItem>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ImageDataItem>
    ) {
        // Load initial data (e.g., first 15 items) and pass it to the callback
        val data = imageDao.getImageWithPagination(params.requestedLoadSize, 0).value
        callback.onResult(data ?: emptyList(), null, 2)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ImageDataItem>) {
        // Load next page of data (e.g., the next 15 items) and pass it to the callback
        val offset = params.key * params.requestedLoadSize
        val data = imageDao.getImageWithPagination(params.requestedLoadSize, offset).value
        callback.onResult(data ?: emptyList(), params.key + 1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ImageDataItem>) {
        // You don't need to load data before the initial load, so you can provide empty data here
        callback.onResult(emptyList(), params.key - 1)
    }
}




