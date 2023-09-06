package com.news.imagedagger.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.news.imagedagger.repository.ImageRepository

class MainViewModelFactory(private val imageRepository: ImageRepository ):ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //return MainViewModel(imageRepository) as T
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(imageRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    }
