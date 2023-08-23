package com.jetpack.submission.movietvshow.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jetpack.submission.movietvshow.model.TvShowModel
import com.jetpack.submission.movietvshow.repository.EntityRepository
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowVMTest{
    private lateinit var viewModel: FavoriteTvShowVM

    @get:Rule
    var instantTaskExecutors = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: EntityRepository

    @Mock
    private lateinit var observer : Observer<PagedList<TvShowModel>>

    @Mock
    private lateinit var pagedList : PagedList<TvShowModel>

    @Before
    fun setup(){
        viewModel = FavoriteTvShowVM(movieRepository)
    }

    @Test
    fun getFavoriteMovies(){
        val dummyTv = pagedList
        Mockito.`when`(dummyTv.size).thenReturn(3)
        val tvShows = MutableLiveData<PagedList<TvShowModel>>()
        tvShows.value = dummyTv

        Mockito.`when`(movieRepository.getFavoriteTv()).thenReturn(tvShows)
        val tvShowEntity = viewModel.getFavoriteTvSHow().value
        verify(movieRepository).getFavoriteTv()
        Assert.assertNotNull(tvShowEntity)
        Assert.assertEquals(3, tvShowEntity?.size)

        viewModel.getFavoriteTvSHow().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}