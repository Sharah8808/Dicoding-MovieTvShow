package com.jetpack.submission.movietvshow.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jetpack.submission.movietvshow.model.MovieModel
import androidx.paging.DataSource
import com.jetpack.submission.movietvshow.model.TvShowModel

@Dao
interface EntityDao {

    @Insert
    fun insertTvShow(tvShowEntity: List<TvShowModel>)

    @Update
    fun updateTvShow(tvShowEntity: TvShowModel)

    @Insert
    fun insertMovie(movieEntity: List<MovieModel>)

    @Update
    fun updateMovie(movieEntity: MovieModel)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies() : DataSource.Factory<Int, MovieModel>

    @Query("SELECT * FROM movie_table WHERE id = :id")
    fun getDetailMovie(id : Int) : LiveData<MovieModel>

    @Query("SELECT * FROM tvShow_table")
    fun getAllTvShows() : DataSource.Factory<Int, TvShowModel>

    @Query("SELECT * FROM tvShow_table WHERE id = :id")
    fun getDetailTv(id : Int) : LiveData<TvShowModel>

    @Query("SELECT * FROM movie_table WHERE favorite = 1")
    fun getListFavoriteMovie() : DataSource.Factory<Int, MovieModel>

    @Query("SELECT * FROM tvShow_table WHERE favorite = 1")
    fun getListFavoriteTvShow() : DataSource.Factory<Int, TvShowModel>

}