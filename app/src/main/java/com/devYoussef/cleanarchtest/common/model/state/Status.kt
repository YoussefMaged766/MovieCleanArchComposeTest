package com.devYoussef.cleanarchtest.common.model.state

import com.devYoussef.cleanarchtest.common.model.exception.HandleExceptions

sealed class Status <out T>{
    data class Loading(val loading: Boolean = true) : Status<Nothing>()
    data class Success<out T>(val data: T) : Status<T>()
    data class Failure(val exception: HandleExceptions) : Status<Nothing>()
}