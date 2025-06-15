package com.devYoussef.cleanarchtest.common.network

import android.util.Log
import com.devYoussef.cleanarchtest.common.custom_components.HandleException
import com.devYoussef.cleanarchtest.common.extentions.fromJson
import com.devYoussef.cleanarchtest.common.model.exception.HandleExceptions
import com.devYoussef.cleanarchtest.common.model.response.ErrorResponse
import com.devYoussef.cleanarchtest.common.model.state.Status
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

inline fun <reified T> safeApiCall(
    crossinline apiCall: suspend () -> Response<T>,
    responseType: Type,
    retryCount: Int = 3
): Flow<Status<T>> = flow {
    emit(Status.Loading())

    try {
        val response = apiCall()

        if (response.isSuccessful) {
            val body = response.body()
                ?: throw HandleExceptions.UnexpectedHttpException(
                    httpErrorCode = response.code(),
                    errorMessage = "Response body is null"
                )

            val parsed = Gson().toJson(body).fromJson<T>(responseType)
            emit(Status.Success(parsed))
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = errorBody ?: "Unknown error"
            throw when (response.code()) {
                HttpURLConnection.HTTP_UNAUTHORIZED -> HandleExceptions.Client.UnauthorizedAccess(errorMessage = errorMessage)
                HttpURLConnection.HTTP_NOT_FOUND -> HandleExceptions.Client.NotFound(errorMessage = errorMessage)
                HttpURLConnection.HTTP_BAD_REQUEST, 422 -> {
                    val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                    val errors = errorResponse.errors?.mapValues { it.value.joinToString() } ?: emptyMap()
                    HandleExceptions.Client.ResponseValidation(errors, errorResponse.message)
                }
                HttpURLConnection.HTTP_UNAVAILABLE,
                HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> HandleExceptions.Server.RetryableServerException(errorMessage = errorMessage)
                in 500..599 -> HandleExceptions.Server.NonRetryableServerException(errorMessage = errorMessage)
                else -> HandleExceptions.UnexpectedHttpException(response.code(), errorMessage)
            }
        }
    } catch (e: Exception) {
        // rethrow to be caught by retryWhen
        throw when (e) {
            is HandleExceptions -> e
            is UnknownHostException -> HandleExceptions.Network.UnknownHost(errorMessage = e.message)
            is SocketTimeoutException -> HandleExceptions.Network.Timeout(errorMessage = e.message)
            is IOException -> HandleExceptions.Local.IOProcess(errorMessage = e.message)
            else -> HandleExceptions.UnknownException(errorMessage = e.message)
        }
    }
}
    .retryWhen { cause, attempt ->
        if (cause is HandleExceptions.Retryable && attempt < retryCount) {
            delay(cause.getRetryDelay())
            true
        } else {
            false
        }
    }
    .catch { e ->
        emit(Status.Failure(e as HandleExceptions))
    }
