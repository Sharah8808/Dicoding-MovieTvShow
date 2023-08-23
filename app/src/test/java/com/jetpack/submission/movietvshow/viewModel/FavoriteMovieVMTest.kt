package com.jetpack.submission.movietvshow.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jetpack.submission.movietvshow.model.MovieModel
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
class FavoriteMovieVMTest{

    private lateinit var viewModel: FavoriteMovieVM

    @get:Rule
    var instantTaskExecutors = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: EntityRepository

    @Mock
    private lateinit var observer : Observer<PagedList<MovieModel>>

    @Mock
    private lateinit var pagedList : PagedList<MovieModel>

    @Before
    fun setup(){
        viewModel = FavoriteMovieVM(movieRepository)
    }

    @Test
    fun getFavoriteMovies(){
        val dummyMovie = pagedList
        Mockito.`when`(dummyMovie.size).thenReturn(3)
        val movies = MutableLiveData<PagedList<MovieModel>>()
        movies.value = dummyMovie

        Mockito.`when`(movieRepository.getFavoriteMovies()).thenReturn(movies)
        val movieEntity = viewModel.getFavoriteMovie().value
        verify(movieRepository).getFavoriteMovies()
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(3, movieEntity?.size)

        viewModel.getFavoriteMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}