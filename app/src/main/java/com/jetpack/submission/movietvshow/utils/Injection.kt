package com.jetpack.submission.movietvshow.utils

import android.content.Context
import com.jetpack.submission.movietvshow.core.LocalDataSource
import com.jetpack.submission.movietvshow.core.RemoteDataSource
import com.jetpack.submission.movietvshow.repository.EntityRepository
import com.jetpack.submission.movietvshow.room.EntityDatabase

object Injection {

    fun provideRepository(context: Context) : EntityRepository {

        val database = EntityDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.entityDao())
        val appExecutors = AppExecutor()

        return EntityRepository.getInstace(remoteDataSource, localDataSource, appExecutors)
    }
}