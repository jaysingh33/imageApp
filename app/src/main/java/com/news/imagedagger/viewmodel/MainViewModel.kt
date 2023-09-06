package com.news.imagedagger.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.imagedagger.model.ImageData
import com.news.imagedagger.model.ImageDataItem
import com.news.imagedagger.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val imageRepository: ImageRepository ): ViewModel() {

    val images: LiveData<List<ImageDataItem>> = imageRepository.images



    fun getLocalImages(): LiveData<List<ImageDataItem>> {
        return imageRepository.localImages
    }

    fun fetchImageData() {
        viewModelScope.launch(Dispatchers.IO) {
            imageRepository.getImage(1, 200)
        }
    }


}