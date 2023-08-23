package com.jetpack.submission.movietvshow.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jetpack.submission.movietvshow.core.BoundServiceNetwork
import com.jetpack.submission.movietvshow.core.LocalDataSource
import com.jetpack.submission.movietvshow.core.RemoteDataSource
import com.jetpack.submission.movietvshow.core.response.Movie
import com.jetpack.submission.movietvshow.core.response.MovieDetail
import com.jetpack.submission.movietvshow.core.response.TvShow
import com.jetpack.submission.movietvshow.core.response.TvShowDetail
import com.jetpack.submission.movietvshow.core.status.ApiResponseStatus
import com.jetpack.submission.movietvshow.core.status.ResourceStatus
import com.jetpack.submission.movietvshow.model.MovieModel
import com.jetpack.submission.movietvshow.model.TvShowModel
import com.jetpack.submission.movietvshow.utils.AppExecutor
import java.lang.StringBuilder

class EntityRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutor: AppExecutor)
    : EntityDataSource {

    companion object {
        private var instance: EntityRepository? = null

        fun getInstace(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutor: AppExecutor
        ): EntityRepository =
            instance ?: synchronized(this) {
                instance ?: EntityRepository(
                    remoteDataSource,
                    localDataSource,
                    appExecutor
                ).apply { instance = this }
            }
    }

    override fun getAllMovies(): LiveData<ResourceStatus<PagedList<MovieModel>>> {
        return object : BoundServiceNetwork<PagedList<MovieModel>, List<Movie>>(appExecutor) {
            override fun loadFromDb(): LiveData<PagedList<MovieModel>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }
            override fun shouldFetch(data: PagedList<MovieModel>?): Boolean =
                data!!.isEmpty() || data == null
            override fun createCall(): LiveData<ApiResponseStatus<List<Movie>>> {
                return remoteDataSource.getAllMovies()
            }
            override fun saveCallResult(dbSource: List<Movie>) {
                val resultMovies = ArrayList<MovieModel>()
                for (response in dbSource) {
                    val movie = MovieModel(
                            response.id,
                            response.title,
                            response.release_date,
                            genre = "",
                            language = "",
                            rating = response.vote,
                            synopsis = response.synopsis,
                            poster = response.poster,
                            false
                    )
                    resultMovies.add(movie)
                }
                localDataSource.insertMovies(resultMovies)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieModel>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getListFavoriteMovie(), config).build()
    }

    override fun getDetailMovies(id: Int): LiveData<ResourceStatus<MovieModel>> {

        return object : BoundServiceNetwork<MovieModel, MovieDetail>(appExecutor) {
            override fun loadFromDb(): LiveData<MovieModel> =
                localDataSource.getDetailMovie(id)

            override fun shouldFetch(data: MovieModel?): Boolean =
                data != null && data.genre == ""

            override fun createCall(): LiveData<ApiResponseStatus<MovieDetail>> =
                remoteDataSource.getDetailMovies(id)

            override fun saveCallResult(dbSource: MovieDetail) {
                val genres = StringBuilder().append("")

                for (i in dbSource.genres.indices) {
                    if (i < dbSource.genres.size - 1) {
                        genres.append("${dbSource.genres[i].name}, ")
                    } else {
                        genres.append(dbSource.genres[i].name)
                    }
                }

                val movie = MovieModel(
                        dbSource.id,
                        dbSource.title,
                        dbSource.release_date,
                        genres.toString(),
                        language = dbSource.languange,
                        dbSource.vote,
                        dbSource.synopsis,
                        dbSource.poster,
                        false
                )
                localDataSource.updateMovie(movie,false)
            }


        }.asLiveData()
    }

    override fun getAllTvShow(): LiveData<ResourceStatus<PagedList<TvShowModel>>> {

        return object : BoundServiceNetwork<PagedList<TvShowModel>, List<TvShow>>(appExecutor) {
            override fun loadFromDb(): LiveData<PagedList<TvShowModel>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponseStatus<List<TvShow>>> {
                return remoteDataSource.getTvShows()
            }

            override fun saveCallResult(dbSource: List<TvShow>) {
                val tvshows = ArrayList<TvShowModel>()
                for (response in dbSource) {
                    val tv = TvShowModel(
                            response.id,
                            response.title,
                            response.release_date,
                            "",
                            "",
                            response.vote,
                            response.synopsis,
                            response.poster,
                            false
                    )
                    tvshows.add(tv)
                }
                localDataSource.insertTvShow(tvshows)
            }

        }.asLiveData()
    }

    override fun getFavoriteTv(): LiveData<PagedList<TvShowModel>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getListFavoriteTvShow(), config).build()
    }

    override fun getDetailTvShow(id: Int): LiveData<ResourceStatus<TvShowModel>> {

        return object : BoundServiceNetwork<TvShowModel, TvShowDetail>(appExecutor) {
            override fun loadFromDb(): LiveData<TvShowModel> {
                return localDataSource.getDetailTv(id)
            }

            override fun shouldFetch(data: TvShowModel?): Boolean {
                return data != null && data.genre == ""
            }

            override fun createCall(): LiveData<ApiResponseStatus<TvShowDetail>> {
                return remoteDataSource.getDetailTvShow(id)
            }

            override fun saveCallResult(dbSource: TvShowDetail) {
                val genre = StringBuilder().append("")

                for (i in dbSource.genres.indices) {
                    if (i < dbSource.genres.size - 1) {
                        genre.append("${dbSource.genres[i].name}, ")
                    } else {
                        genre.append(dbSource.genres[i].name)
                    }
                }

                val tv = TvShowModel(
                        dbSource.id,
                        dbSource.title,
                        dbSource.release_date,
                        genre.toString(),
                        dbSource.languange,
                        dbSource.vote,
                        dbSource.synopsis,
                        dbSource.poster,
                        false
                )
                localDataSource.updateTv(tv, false)
            }

        }.asLiveData()


    }

    override fun setFavoriteMovie(movieEntity: MovieModel, state : Boolean) {
        appExecutor.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

    override fun setFavoriteTv(tvShowEntity: TvShowModel, state : Boolean) {
        appExecutor.diskIO().execute { localDataSource.setFavoriteTvSHow(tvShowEntity, state) }
    }
}