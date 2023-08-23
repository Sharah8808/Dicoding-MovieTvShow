package com.jetpack.submission.movietvshow.core.status

class ApiResponseStatus<T>(val status : Status, val body : T, val message : String?) {
    companion object{
        fun <T> success(body: T) : ApiResponseStatus<T> = ApiResponseStatus(Status.SUCCESS, body, null)
        fun <T> error(body: T, msg : String) : ApiResponseStatus<T> = ApiResponseStatus(Status.ERROR, body, msg)
        fun <T> empty(body: T, msg : String) : ApiResponseStatus<T> = ApiResponseStatus(Status.EMPTY, body, msg)
    }
}