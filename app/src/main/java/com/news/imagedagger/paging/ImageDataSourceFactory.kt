package com.news.imagedagger.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.news.imagedagger.db.ImageDao
import com.news.imagedagger.model.ImageDataItem


class ImageDataSourceFactory(private val imageDao: ImageDao) : DataSource.Factory<Int, ImageDataItem>() {

    val dataSourceLiveData = MutableLiveData<ImageDataSource>()

    override fun create(): DataSource<Int, ImageDataItem> {
        val dataSource = ImageDataSource(imageDao)
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }
}
