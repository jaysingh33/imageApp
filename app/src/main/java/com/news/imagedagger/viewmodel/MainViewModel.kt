package com.news.imagedagger.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.news.imagedagger.model.ImageData
import com.news.imagedagger.model.ImageDataItem
import com.news.imagedagger.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch


class MainViewModel(private val imageRepository: ImageRepository) : ViewModel() {
    private val refreshTrigger = MutableStateFlow(Unit)

    val images: Flow<PagingData<ImageDataItem>> = refreshTrigger.flatMapLatest {
        imageRepository.getImages()
            .cachedIn(viewModelScope)
    }

    fun refreshData() {
        refreshTrigger.value = Unit
    }

//    val images: Flow<PagingData<ImageDataItem>> = imageRepository.getImages()
//        .cachedIn(viewModelScope)

}
