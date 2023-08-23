package com.jetpack.submission.movietvshow.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jetpack.submission.movietvshow.core.status.ResourceStatus
import com.jetpack.submission.movietvshow.model.MovieModel
import com.jetpack.submission.movietvshow.model.TvShowModel
import com.jetpack.submission.movietvshow.repository.EntityRepository
import com.jetpack.submission.movietvshow.utils.Dummy
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private var dataTvShow = Dummy.getDummyTvShow()[0]
    private var dataMovie = Dummy.getDummyMovie()[0]

    private var tvShowId = dataTvShow.id
    private var movieId = dataMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var entityRepo : EntityRepository

    @Mock
    private lateinit var observerTvShow : Observer<ResourceStatus<TvShowModel>>

    @Mock
    private lateinit var observerMovie : Observer<ResourceStatus<MovieModel>>

    @Before
    fun setUp(){
        viewModel = DetailViewModel(entityRepo )
    }

    @Test
    fun getMovie() {
        val detailMovie = ResourceStatus.success(Dummy.getDetailMovie())
        val movie = MutableLiveData<ResourceStatus<MovieModel>>()
        movie.value = detailMovie

        `when`(movieId?.let { entityRepo.getDetailMovies(it) }).thenReturn(movie)
        movieId?.let { viewModel.getDetailMovie(it) }
        viewModel.getMovie().observeForever(observerMovie)
        movieId?.let { com.nhaarman.mockitokotlin2.verify(entityRepo).getDetailMovies(it) }
        com.nhaarman.mockitokotlin2.verify(observerMovie).onChanged(detailMovie)
    }

    @Test
    fun setFavoriteMovie(){
        val dummyMovie = ResourceStatus.success(Dummy.getDetailMovie())
        val movies = MutableLiveData<ResourceStatus<MovieModel>>()
        movies.value = dummyMovie

        `when`(movieId?.let { entityRepo.getDetailMovies(it) }).thenReturn(movies)
        movieId?.let { viewModel.getDetailMovie(it) }
        viewModel.setFavoriteMovie()
        com.nhaarman.mockitokotlin2.verify(entityRepo)
            .setFavoriteMovie(movies.value!!.data as MovieModel, true)
        verifyNoMoreInteractions(observerMovie)
    }

    @Test
    fun getTvShow() {
        val detailTv = ResourceStatus.success(Dummy.getDetailTv())
        val tv = MutableLiveData<ResourceStatus<TvShowModel>>()
        tv.value = detailTv

        `when`(tvShowId?.let { entityRepo.getDetailTvShow(it) }).thenReturn(tv)
        tvShowId?.let { viewModel.getDetailTv(it) }
        viewModel.getTvShow().observeForever(observerTvShow)
        tvShowId?.let { com.nhaarman.mockitokotlin2.verify(entityRepo).getDetailTvShow(it) }
        com.nhaarman.mockitokotlin2.verify(observerTvShow).onChanged(detailTv)
    }

    @Test
    fun setFavoriteTvShow(){
        val dummyTv = ResourceStatus.success(Dummy.getDetailTv())
        val tvShow = MutableLiveData<ResourceStatus<TvShowModel>>()
        tvShow.value = dummyTv

        `when`(tvShowId?.let { entityRepo.getDetailTvShow(it) }).thenReturn(tvShow)
        tvShowId?.let { viewModel.getDetailTv(it) }
        viewModel.setFavoriteTvShow()
        com.nhaarman.mockitokotlin2.verify(entityRepo)
            .setFavoriteTv(tvShow.value!!.data as TvShowModel, true)
        verifyNoMoreInteractions(observerTvShow)
    }
}