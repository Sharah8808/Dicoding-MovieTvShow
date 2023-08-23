package com.jetpack.submission.movietvshow.utils

import com.jetpack.submission.movietvshow.model.MovieModel

interface ClickCallbackMovie {
    fun onShareClickMovie(entity: MovieModel)
}