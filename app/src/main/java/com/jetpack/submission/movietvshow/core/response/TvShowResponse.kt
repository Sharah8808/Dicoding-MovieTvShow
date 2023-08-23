package com.jetpack.submission.movietvshow.core.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @SerializedName("results")
    val result : List<TvShow>
)