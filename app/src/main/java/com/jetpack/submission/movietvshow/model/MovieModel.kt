package com.jetpack.submission.movietvshow.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "movie_table")
@Parcelize
data class MovieModel (
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = 0,
    @ColumnInfo(name = "title")
    var title: String? = null,
    @ColumnInfo(name = "release_date")
    var release_date: String? = null,
    @ColumnInfo(name = "genre")
    var genre: String? = null,
    @ColumnInfo(name = "language")
    var language: String? = null,
    @ColumnInfo(name = "rating")
    var rating: Double? = null,
    @ColumnInfo(name = "synopsis")
    var synopsis: String? = null,
    @ColumnInfo(name = "poster")
    var poster: String? = null,
    @ColumnInfo(name = "favorite")
    var favorite : Boolean? = null
): Parcelable