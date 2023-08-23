package com.jetpack.submission.movietvshow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jetpack.submission.movietvshow.model.TvShowModel
import com.jetpack.submission.movietvshow.repository.EntityRepository

class FavoriteTvShowVM(private val movieRepository: EntityRepository) : ViewModel() {
    fun getFavoriteTvSHow() : LiveData<PagedList<TvShowModel>> = movieRepository.getFavoriteTv()
}