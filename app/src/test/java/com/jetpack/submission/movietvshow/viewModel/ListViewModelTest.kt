package com.jetpack.submission.movietvshow.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jetpack.submission.movietvshow.core.status.ResourceStatus
import com.jetpack.submission.movietvshow.model.MovieModel
import com.jetpack.submission.movietvshow.model.TvShowModel
import com.jetpack.submission.movietvshow.repository.EntityRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ListViewModelTest {

    private lateinit var viewModel: ListViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var entityRepo: EntityRepository

    @Mock
    private lateinit var observerMovie: Observer<ResourceStatus<PagedList<MovieModel>>>

    @Mock
    private lateinit var observerTvShow: Observer<ResourceStatus<PagedList<TvShowModel>>>

    @Mock
    private lateinit var pagedListTv : PagedList<TvShowModel>

    @Mock
    private lateinit var pagedListMov : PagedList<MovieModel>

    @Before
    fun setUp(){
        viewModel = ListViewModel(entityRepo)
    }

    @Test
    fun getMovies(){
        val dummyMovies = ResourceStatus.success(pagedListMov)
        Mockito.`when`(dummyMovies.data?.size).thenReturn(3)
        val movie = MutableLiveData<ResourceStatus<PagedList<MovieModel>>>()
        movie.value = dummyMovies


        Mockito.`when`(entityRepo.getAllMovies()).thenReturn(movie)
        val movies = viewModel.getMovieList().value?.data
        Mockito.verify(entityRepo).getAllMovies()
        Assert.assertNotNull(movies)
        Assert.assertEquals(3, movies?.size)

        viewModel.getMovieList().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(dummyMovies)
    }

    @Test
    fun getTvShows() {
        val dummyTv = ResourceStatus.success(pagedListTv)
        Mockito.`when`(dummyTv.data?.size).thenReturn(3)
        val tv = MutableLiveData<ResourceStatus<PagedList<TvShowModel>>>()
        tv.value = dummyTv

        Mockito.`when`(entityRepo.getAllTvShow()).thenReturn(tv)
        val tvShow = viewModel.getTvList().value?.data
        Mockito.verify(entityRepo).getAllTvShow()
        Assert.assertNotNull(tvShow)
        Assert.assertEquals(3, tvShow?.size)

        viewModel.getTvList().observeForever(observerTvShow)
        Mockito.verify(observerTvShow).onChanged(dummyTv)
    }
}