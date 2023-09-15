package com.news.imagedagger.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.news.imagedagger.view.DetailsActivity
import com.news.imagedagger.R
import com.news.imagedagger.model.ImageDataItem


class ImageAdapter(private val context: Context) :
    PagingDataAdapter<ImageDataItem, ImageAdapter.ImageViewHolder>(MyItemDiffCallback()) {
    private lateinit var lifecycle: Lifecycle
    private var imageData: List<ImageDataItem> = emptyList()

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        //val imageItem = imageData[position]
        val imageItem = getItem(position)

        holder.authorTextView.text = imageItem?.author

        //Log.d("$$", "$$" + imageItem.width + imageItem.height)

        // Load and display the image using a library like Glide or Picasso

        Glide.with(context)
            .load(imageItem?.download_url)
            .into(holder.imageView)


        val wit = imageItem?.width
        val hig = imageItem?.height
        // Log.d("##", "##" + wit + hig)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("author", imageItem?.author)
            intent.putExtra("imageUrl", imageItem?.download_url)
            intent.putExtra("width", wit)
            intent.putExtra("height", hig)
            context.startActivity(intent)
        }
    }
    fun updateData(newImageData: List<ImageDataItem>) {
        imageData = newImageData
        notifyDataSetChanged()
    }

}

