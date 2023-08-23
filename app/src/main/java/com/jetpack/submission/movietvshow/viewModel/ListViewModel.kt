package com.jetpack.submission.movietvshow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jetpack.submission.movietvshow.core.status.ResourceStatus
import com.jetpack.submission.movietvshow.model.MovieModel
import com.jetpack.submission.movietvshow.model.TvShowModel
import com.jetpack.submission.movietvshow.repository.EntityRepository

class ListViewModel (private val entityRepository: EntityRepository): ViewModel() {

    //get all movie
//    fun getMovieList():LiveData<List<Entity>> = entityRepository.getAllMovies()
    fun getMovieList() : LiveData<ResourceStatus<PagedList<MovieModel>>> = entityRepository.getAllMovies()

    //get all tvShow
//    fun getTvList() : LiveData<List<Entity>> = entityRepository.getAllTvShow()
    fun getTvList() : LiveData<ResourceStatus<PagedList<TvShowModel>>> = entityRepository.getAllTvShow()
}