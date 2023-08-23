package com.jetpack.submission.movietvshow.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object Poster {

    const val API_IMAGE = "https://image.tmdb.org/t/p/"
    const val IMAGE_SIZE = "w500"

    fun setImage(context: Context, imagePath : String, imageView: ImageView){
        Glide.with(context).clear(imageView)
        Glide.with(context)
            .load(imagePath)
            .into(imageView)
    }
}