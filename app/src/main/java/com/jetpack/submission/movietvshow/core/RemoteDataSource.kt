package com.jetpack.submission.movietvshow.core

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jetpack.submission.movietvshow.core.api.ApiConfig
import com.jetpack.submission.movietvshow.utils.EspressoIdlingResource
import com.jetpack.submission.movietvshow.core.response.*
import com.jetpack.submission.movietvshow.core.status.ApiResponseStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        private val TAG = RemoteDataSource::class.java.simpleName
        private var instance : RemoteDataSource? = null

        fun getInstance() : RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getAllMovies() : LiveData<ApiResponseStatus<List<Movie>>> {
        EspressoIdlingResource.increment()
        val listMovies = MutableLiveData<ApiResponseStatus<List<Movie>>>()
        val client = ApiConfig.getApiService().getApiAllMovie()

        client.enqueue( object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                listMovies.value = ApiResponseStatus.success(response.body()?.result!!)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d(TAG, t.message.toString())
                EspressoIdlingResource.decrement()
            }

        })
        return listMovies
    }

    fun getDetailMovies(id : Int) : LiveData<ApiResponseStatus<MovieDetail>> {
        EspressoIdlingResource.increment()
        val detailMovie = MutableLiveData<ApiResponseStatus<MovieDetail>>()
        val client = ApiConfig.getApiService().getApiDetailMovie(id)

        client.enqueue(object : Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                detailMovie.value = ApiResponseStatus.success(response.body() as MovieDetail)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                Log.d(TAG, t.message.toString())
                EspressoIdlingResource.decrement()
            }
        })
        return detailMovie
    }

    fun getTvShows() : LiveData<ApiResponseStatus<List<TvShow>>> {
        EspressoIdlingResource.increment()
        val listTvShow = MutableLiveData<ApiResponseStatus<List<TvShow>>>()
        val client = ApiConfig.getApiService().getApiAllTvShow()

        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                listTvShow.value = ApiResponseStatus.success(response.body()?.result as List<TvShow>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.d(TAG, t.message.toString())
                EspressoIdlingResource.decrement()
            }
        })

        return listTvShow
    }

    fun getDetailTvShow(id : Int) : LiveData<ApiResponseStatus<TvShowDetail>> {
        EspressoIdlingResource.increment()
        val detailTv = MutableLiveData<ApiResponseStatus<TvShowDetail>>()
        val client = ApiConfig.getApiService().getApiDetailTvShow(id)

        client.enqueue(object : Callback<TvShowDetail> {
            override fun onResponse(call: Call<TvShowDetail>, response: Response<TvShowDetail>) {
                detailTv.value = ApiResponseStatus.success(response.body() as TvShowDetail)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowDetail>, t: Throwable) {
                Log.d(TAG, t.message.toString())
                EspressoIdlingResource.decrement()
            }

        })

        return detailTv
    }
}