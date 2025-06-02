package com.devYoussef.cleanarchtest.common.interfaces

import com.devYoussef.cleanarchtest.common.model.exception.HandleExceptions

interface ExceptionDelegate {

    fun handleException(
        exception: HandleExceptions,
        onRetry: (() -> Unit)
    )
}