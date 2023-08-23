package com.jetpack.submission.movietvshow.core.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    var result : List<Movie>
)