package com.jetpack.submission.movietvshow.core.response

import com.google.gson.annotations.SerializedName

data class TvShowDetail(
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
    var poster : String,

    @SerializedName("genres")
    val genres : List<Genre>,

    @SerializedName("original_language")
    var languange : String,
)