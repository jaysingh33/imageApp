package com.news.imagedagger.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.news.imagedagger.R


class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent = intent
        val author = intent.getStringExtra("author")
        val imageUrl = intent.getStringExtra("imageUrl")
        val width = intent.getIntExtra("width",0)
        val hight = intent.getIntExtra("height",0)
        val authorTextView: TextView = findViewById(R.id.authorTextView)
        val widthTextView: TextView = findViewById(R.id.widthTextView)
        val hightTextView: TextView = findViewById(R.id.hightTextView)
        val imageView: ImageView = findViewById(R.id.imageView)
        val closeButton: ImageView = findViewById(R.id.back)

        authorTextView.text = author
        widthTextView.text = width.toString()
        hightTextView.text = hight.toString()

//        Toast.makeText(this, "Internet "+hight+","+width, Toast.LENGTH_SHORT)
//            .show()
        Glide.with(this)
            .load(imageUrl)
            .into(imageView)


        closeButton.setOnClickListener {
            finish()
        }
    }

}
