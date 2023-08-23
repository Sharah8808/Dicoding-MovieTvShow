package com.jetpack.submission.movietvshow.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jetpack.submission.movietvshow.core.status.ResourceStatus
import com.jetpack.submission.movietvshow.model.MovieModel
import com.jetpack.submission.movietvshow.model.TvShowModel

interface EntityDataSource {

    fun getAllMovies() : LiveData<ResourceStatus<PagedList<MovieModel>>>

    fun getFavoriteMovies() : LiveData<PagedList<MovieModel>>

    fun getDetailMovies(id : Int) : LiveData<ResourceStatus<MovieModel>>

    fun getAllTvShow() : LiveData<ResourceStatus<PagedList<TvShowModel>>>

    fun getFavoriteTv() : LiveData<PagedList<TvShowModel>>

    fun getDetailTvShow(id : Int) : LiveData<ResourceStatus<TvShowModel>>

    fun setFavoriteMovie(movieEntity: MovieModel, state : Boolean)

    fun setFavoriteTv(tvShowEntity: TvShowModel, state : Boolean)

}