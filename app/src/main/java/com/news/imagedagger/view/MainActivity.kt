package com.news.imagedagger.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.news.imagedagger.R
import com.news.imagedagger.adapter.ImageAdapter
import com.news.imagedagger.model.ImageDataItem
import com.news.imagedagger.utils.ImageApplication
import com.news.imagedagger.utils.NetworkUtils
import com.news.imagedagger.viewmodel.MainViewModel
import com.news.imagedagger.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var syncData: ImageView
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as ImageApplication).imageRepository)
    }
    private val imageAdapter = ImageAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        syncData = findViewById(R.id.syncData)
        val animation = AnimationUtils.loadAnimation(this, R.anim.scale_animation)

        recyclerView.layoutManager =
            GridLayoutManager(this, 2) // Adjust the number of columns as needed
        recyclerView.adapter = imageAdapter



        syncData.setOnClickListener {

            if (NetworkUtils.isInternetAvailable(this)) {
                syncData.startAnimation(animation)
                viewModel.fetchImageData()
                updateAdapterData(emptyList())

            } else {
                Toast.makeText(this, "Internet not available Please Check", Toast.LENGTH_SHORT)
                    .show()
            }
        }


        viewModel.images.observe(this) { imageDataList ->
            imageAdapter.setImageData(imageDataList)
        }

        viewModel.getLocalImages().observe(this) { localImageDataList ->
            imageAdapter.setLocalImageData(localImageDataList)
        }

        viewModel.fetchImageData()

    }
    private fun updateAdapterData(newImageData: List<ImageDataItem>) {
        imageAdapter.updateData(newImageData)
    }

}