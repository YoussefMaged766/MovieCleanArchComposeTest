package com.devYoussef.cleanarchtest.common.network

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
import java.lang.reflect.Type
import java.net.HttpURLConnection

inline fun <reified T> safeApiCall(
    crossinline apiCall: suspend () -> Response<T>,
    responseType: Type,
    retryCount: Int = 3
): Flow<Status<T>> = flow {
    emit(Status.Loading())
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
            HttpURLConnection.HTTP_UNAUTHORIZED -> HandleExceptions.Client.UnauthorizedAccess(
                errorMessage = errorMessage
            )

            HttpURLConnection.HTTP_NOT_FOUND -> HandleExceptions.Client.NotFound(errorMessage = errorMessage)
            HttpURLConnection.HTTP_BAD_REQUEST, 422 -> {
                val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                val errors =
                    errorResponse.errors?.mapValues { it.value.joinToString() } ?: emptyMap()
                HandleExceptions.Client.ResponseValidation(
                    fieldErrors = errors,
                    errorMessage = errorResponse.message
                )
            }

            HttpURLConnection.HTTP_UNAVAILABLE,
            HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> HandleExceptions.Server.RetryableServerException(
                errorMessage = errorMessage
            )

            in 500..599 -> HandleExceptions.Server.NonRetryableServerException(errorMessage = errorMessage)
            else -> HandleExceptions.UnexpectedHttpException(response.code(), errorMessage)
        }
    }
}.retryWhen { cause, attempt ->
    if ((cause as? HandleExceptions)?.isRetryable() == true && attempt < retryCount) {
        val retryDelay = (cause as? HandleExceptions.Retryable)?.getRetryDelay() ?: 1000L
        delay(retryDelay)
        true
    } else {
        false
    }
}.catch { e ->
    emit(Status.Failure(HandleExceptions.UnknownException(e.message ?: "Unknown error")))
}