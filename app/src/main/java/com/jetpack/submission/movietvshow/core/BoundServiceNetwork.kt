package com.jetpack.submission.movietvshow.core

import android.widget.Toast
import com.jetpack.submission.movietvshow.core.status.ResourceStatus
import com.jetpack.submission.movietvshow.utils.AppExecutor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.jetpack.submission.movietvshow.core.status.ApiResponseStatus
import com.jetpack.submission.movietvshow.core.status.Status
import kotlin.coroutines.coroutineContext

abstract class BoundServiceNetwork<ResultType, RequestType>(private val appExecutors: AppExecutor){
    private val result = MediatorLiveData<ResourceStatus<ResultType>>()

    init {
        result.value = ResourceStatus.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDb()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)){
                fetchFromNetwork(dbSource)
            }else {
                result.addSource(dbSource) { newData ->
                    result.value = ResourceStatus.success(newData)
                }
            }
        }
    }

    protected fun onFetchFailed() {}

    protected abstract fun loadFromDb() : LiveData<ResultType>

    protected abstract fun shouldFetch(data : ResultType?) : Boolean

    protected abstract fun createCall() : LiveData<ApiResponseStatus<RequestType>>

    protected abstract fun saveCallResult(dbSource : RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource){ newData ->
            result.value = ResourceStatus.loading(newData)
        }

        result.addSource(apiResponse){ response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when(response.status) {
                Status.SUCCESS -> {
                    appExecutors.diskIO().execute {
                        response.body?.let { saveCallResult(it) }
                        appExecutors.mainThread().execute{
                            result.addSource(dbSource) { newData ->
                                result.value = ResourceStatus.success(newData)
                            }
                        }
                    }
                }
                Status.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) {
                        result.value = ResourceStatus.error(it, response.message!!)
                    }
                }
            }
        }
    }

    fun asLiveData() : LiveData<ResourceStatus<ResultType>> = result

}