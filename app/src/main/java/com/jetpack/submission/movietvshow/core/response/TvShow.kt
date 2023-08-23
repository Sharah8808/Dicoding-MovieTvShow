package com.jetpack.submission.movietvshow.core.response

import com.google.gson.annotations.SerializedName

data class TvShow(
    @SerializedName("id")
    var id : Int,

    @SerializedName("name")
    var title : String,

    @SerializedName("first_air_date")
    var release_date: String,

    @SerializedName("vote_average")
    var vote : Double,

    @SerializedName("overview")
    var synopsis : String,

    @SerializedName("poster_path")
    var poster : String
)
