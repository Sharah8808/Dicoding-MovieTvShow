package com.jetpack.submission.movietvshow.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jetpack.submission.movietvshow.core.LocalDataSource
import com.jetpack.submission.movietvshow.core.RemoteDataSource
import com.jetpack.submission.movietvshow.core.status.ResourceStatus
import com.jetpack.submission.movietvshow.model.MovieModel
import com.jetpack.submission.movietvshow.model.TvShowModel
import com.jetpack.submission.movietvshow.utils.AppExecutor
import com.jetpack.submission.movietvshow.utils.Dummy
import com.jetpack.submission.movietvshow.utils.LiveData
import com.jetpack.submission.movietvshow.utils.PageListUtils
import com.nhaarman.mockitokotlin2.*
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class EntityRepositoryTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutor::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)

    private val dumDumRepo = DummyRepositoryTest(remote, local, appExecutors)

    private val movieResponse = Dummy.generateMovieResponse()
    private val movieId = movieResponse[0].id
    private val movieDetailResponse = Dummy.generateDetailMovie()[0]

    private val tvShowResponse = Dummy.generateTvShowResponse()
    private val tvShowId = tvShowResponse[0].id
    private val tvDetailResponse = Dummy.generateDetailTv()[0]

    @Test
    fun getAllMovies() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieModel>
        Mockito.`when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        dumDumRepo.getAllMovies()

        val moviesEntity = ResourceStatus.success(PageListUtils.mockPagedList(Dummy.getDummyMovie()))
        verify(local).getAllMovies()
        TestCase.assertNotNull(moviesEntity.data)
        assertEquals(movieResponse.size.toLong(), moviesEntity.data?.size?.toLong())
    }

    @Test
    fun getAllTvShow(){
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowModel>
        Mockito.`when`(local.getAllTvShow()).thenReturn(dataSourceFactory)
        dumDumRepo.getAllTvShow()

        val tvEntity = ResourceStatus.success(PageListUtils.mockPagedList(Dummy.getDummyTvShow()))
        verify(local).getAllTvShow()
        TestCase.assertNotNull(tvEntity)
        assertEquals(tvShowResponse.size.toLong(), tvEntity.data?.size?.toLong())
    }

    @Test
    fun getDetailMovies(){
        val dummyMovies = MutableLiveData<MovieModel>()
        dummyMovies.value = Dummy.getDetailMovie()
        Mockito.`when`(local.getDetailMovie(movieId)).thenReturn(dummyMovies)

        val movieDetail = LiveData.getValues(dumDumRepo.getDetailMovies(movieId))
        verify(local).getDetailMovie(movieId)

        TestCase.assertNotNull(movieDetail)
        assertEquals(movieDetailResponse.id, movieDetail.data?.id)
    }

    @Test
    fun getDetailTv(){
        val dummyTv = MutableLiveData<TvShowModel>()
        dummyTv.value = Dummy.getDetailTv()
        Mockito.`when`(local.getDetailTv(tvShowId)).thenReturn(dummyTv)

        val tvShowDetail = LiveData.getValues(dumDumRepo.getDetailTvShow(tvShowId))
        verify(local).getDetailTv(tvShowId)

        TestCase.assertNotNull(tvShowDetail)
        assertEquals(tvDetailResponse.id, tvShowDetail.data?.id)
    }

    @Test
    fun getFavoriteMovie(){
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieModel>
        Mockito.`when`(local.getListFavoriteMovie()).thenReturn(dataSourceFactory)
        dumDumRepo.getFavoriteMovies()

        val movieEntity = ResourceStatus.success(PageListUtils.mockPagedList(Dummy.getDummyMovie()))
        verify(local).getListFavoriteMovie()
        TestCase.assertNotNull(movieEntity)
        assertEquals(movieResponse.size, movieEntity.data?.size)
    }

    @Test
    fun getFavoriteTvShow(){
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowModel>
        Mockito.`when`(local.getListFavoriteTvShow()).thenReturn(dataSourceFactory)
        dumDumRepo.getFavoriteTv()

        val tvShowEntity = ResourceStatus.success(PageListUtils.mockPagedList(Dummy.getDummyTvShow()))
        verify(local).getListFavoriteTvShow()
        TestCase.assertNotNull(tvShowEntity)
        assertEquals(tvShowResponse.size, tvShowEntity.data?.size)
    }

    @Test
    fun setFavoriteMovies(){
        dumDumRepo.setFavoriteMovie(Dummy.getDetailMovie(), true)
        verify(local).setFavoriteMovie(Dummy.getDetailMovie(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun setFavoriteTv(){
        dumDumRepo.setFavoriteTv(Dummy.getDetailTv(), true)
        verify(local).setFavoriteTvSHow(Dummy.getDetailTv(), true)
        verifyNoMoreInteractions(local)
    }
}