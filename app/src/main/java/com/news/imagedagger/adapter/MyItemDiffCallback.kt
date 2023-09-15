package com.news.imagedagger.adapter

import androidx.recyclerview.widget.DiffUtil
import com.news.imagedagger.model.ImageDataItem

class MyItemDiffCallback : DiffUtil.ItemCallback<ImageDataItem>() {
    override fun areItemsTheSame(oldItem: ImageDataItem, newItem: ImageDataItem): Boolean {
        // Return true if the items have the same identifier.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageDataItem, newItem: ImageDataItem): Boolean {
        // Return true if the item content (excluding the identifier) is the same.
        return oldItem == newItem
    }
}