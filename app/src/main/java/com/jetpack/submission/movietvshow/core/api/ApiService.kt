package com.jetpack.submission.movietvshow.core.api

import com.jetpack.submission.movietvshow.BuildConfig
import com.jetpack.submission.movietvshow.core.response.MovieDetail
import com.jetpack.submission.movietvshow.core.response.MovieResponse
import com.jetpack.submission.movietvshow.core.response.TvShowDetail
import com.jetpack.submission.movietvshow.core.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getApiAllMovie(
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("page") page : String = "1",
        @Query("language") language : String = "en-US",
    ) : Call<MovieResponse>

    @GET("tv/popular")
    fun getApiAllTvShow(
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("page") page : String = "1",
        @Query("language") language : String = "en-US",
    ) : Call<TvShowResponse>

    @GET("movie/{movie_id}")
    fun getApiDetailMovie(
        @Path("movie_id") id : Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ) : Call<MovieDetail>

    @GET("tv/{tv_id}")
    fun getApiDetailTvShow(
        @Path("tv_id") id : Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ) : Call<TvShowDetail>


}