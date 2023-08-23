package com.jetpack.submission.movietvshow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jetpack.submission.movietvshow.core.status.ResourceStatus
import com.jetpack.submission.movietvshow.model.MovieModel
import com.jetpack.submission.movietvshow.model.TvShowModel
import com.jetpack.submission.movietvshow.repository.EntityRepository

class DetailViewModel (private var entityRepository: EntityRepository): ViewModel() {

    //Bedakan kondisi untuk movie dan tv show!

    private lateinit var movieId  :LiveData<ResourceStatus<MovieModel>>
    private lateinit var tvShowId : LiveData<ResourceStatus<TvShowModel>>
    fun getTvShow() = tvShowId
    fun getMovie() = movieId

    fun getDetailTv(id : Int){
        tvShowId = entityRepository.getDetailTvShow(id)
    }

    fun getDetailMovie(id : Int) {
        movieId = entityRepository.getDetailMovies(id)
    }

    fun setFavoriteTvShow(){
        val tvShowEntity = tvShowId.value
        if (tvShowEntity?.data != null){
            val newState = !tvShowEntity.data.favorite!!
            entityRepository.setFavoriteTv(tvShowEntity.data, newState)
        }
    }

    fun setFavoriteMovie(){
        val movieEntity = movieId.value
        if (movieEntity?.data != null){
            val newState = !movieEntity.data.favorite!!
            entityRepository.setFavoriteMovie(movieEntity.data, newState)
        }
    }

}