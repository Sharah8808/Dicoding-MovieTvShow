package com.jetpack.submission.movietvshow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jetpack.submission.movietvshow.model.MovieModel
import com.jetpack.submission.movietvshow.repository.EntityRepository

class FavoriteMovieVM (private val movieRepository: EntityRepository) : ViewModel() {
    fun getFavoriteMovie() : LiveData<PagedList<MovieModel>> = movieRepository.getFavoriteMovies()
}