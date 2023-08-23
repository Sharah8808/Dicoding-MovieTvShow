package com.jetpack.submission.movietvshow.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutor private constructor(
    private val diskIO: Executor,
    private val mainThread: Executor
){
    //BIAR BSA BACA DATA DRI THREAD YG BERBEDA

    constructor() : this (
        Executors.newSingleThreadExecutor(),
        MainThreadExecutor()
    )

    fun diskIO() : Executor = diskIO

    fun mainThread() : Executor = mainThread

    private class MainThreadExecutor() : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}