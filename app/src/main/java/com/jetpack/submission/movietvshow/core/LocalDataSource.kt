package com.jetpack.submission.movietvshow.core

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.jetpack.submission.movietvshow.model.MovieModel
import com.jetpack.submission.movietvshow.model.TvShowModel
import com.jetpack.submission.movietvshow.room.EntityDao

class LocalDataSource private constructor(private val entityDao: EntityDao){

    companion object{
        private var INSTANCE : LocalDataSource? = null

        fun getInstance(entityDao: EntityDao) : LocalDataSource =
            INSTANCE ?: LocalDataSource(entityDao)
    }

    fun updateMovie(movie: MovieModel, newState: Boolean) {
        movie.favorite = newState
        entityDao.updateMovie(movie)
    }

    fun updateTv(tvShow: TvShowModel, newState: Boolean) {
        tvShow.favorite = newState
        entityDao.updateTvShow(tvShow)
    }

    fun getAllMovies() : DataSource.Factory<Int, MovieModel> = entityDao.getAllMovies()

    fun getAllTvShow() : DataSource.Factory<Int, TvShowModel> = entityDao.getAllTvShows()

    fun getListFavoriteMovie() : DataSource.Factory<Int, MovieModel> = entityDao.getListFavoriteMovie()

    fun getListFavoriteTvShow() : DataSource.Factory<Int, TvShowModel> = entityDao.getListFavoriteTvShow()

    fun getDetailMovie(id : Int) : LiveData<MovieModel> = entityDao.getDetailMovie(id)

    fun getDetailTv(id : Int) : LiveData<TvShowModel> = entityDao.getDetailTv(id)

    fun insertMovies(movieEntity: List<MovieModel>) = entityDao.insertMovie(movieEntity)

    fun insertTvShow(tvShowEntity: List<TvShowModel>) = entityDao.insertTvShow(tvShowEntity)

    fun setFavoriteMovie(movie : MovieModel, newState : Boolean){
        movie.favorite = newState
        entityDao.updateMovie(movie)
    }

    fun setFavoriteTvSHow(tvShow : TvShowModel, newState : Boolean) {
        tvShow.favorite = newState
        entityDao.updateTvShow((tvShow))
    }
}