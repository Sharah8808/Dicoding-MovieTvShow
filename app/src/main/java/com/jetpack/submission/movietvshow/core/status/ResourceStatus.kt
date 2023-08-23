package com.jetpack.submission.movietvshow.core.status

data class ResourceStatus<T>(val status: Status, val data : T?, val message : String?){
    companion object{
        fun<T> success(data : T?) : ResourceStatus<T> = ResourceStatus(Status.SUCCESS, data, null)
        fun<T> error(data : T, msg : String) : ResourceStatus<T> = ResourceStatus(Status.ERROR, data, msg)
        fun<T> loading(data : T?) : ResourceStatus<T> = ResourceStatus(Status.LOADING, data, null)
    }
}