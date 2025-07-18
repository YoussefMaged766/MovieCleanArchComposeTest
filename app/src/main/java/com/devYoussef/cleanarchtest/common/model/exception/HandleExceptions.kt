package com.devYoussef.cleanarchtest.common.model.exception

import androidx.annotation.StringRes
import com.devYoussef.cleanarchtest.R

sealed class HandleExceptions(@StringRes open val messageRes: Int? = null, message: String?) :
    Exception(message) {
    /**
     * Interface for exceptions that can be retried. It provides a method to get the delay before retrying.
     */
    interface Retryable {
        /**
         * Gets the delay in milliseconds before retrying the operation that caused the exception.
         *
         * @return The retry delay in milliseconds.
         */
        fun getRetryDelay(): Long  // Time in milliseconds
    }

    // Network-related exceptionss
    sealed class Network(
        @StringRes messageRes: Int? = null,
        message: String?
    ) : HandleExceptions(messageRes, message) {

        /**
         * Represents a network timeout exception.
         * This exception is retryable, and the retry delay is set to 3000ms.
         */
        data class Timeout(
            @StringRes override val messageRes: Int = R.string.error_timeout,
            val errorMessage: String? = null
        ) : Network(messageRes, errorMessage), Retryable {
            override fun getRetryDelay() = 3000L
        }

        /**
         * Represents an unknown host exception.
         * This exception is retryable, and the retry delay is set to 2000ms.
         */
        data class UnknownHost(
            @StringRes override val messageRes: Int = R.string.error_unknown_host,
            val errorMessage: String? = null
        ) : Network(messageRes, errorMessage), Retryable {
            override fun getRetryDelay() = 2000L
        }


//        data class OfflineNetwork(
//            @StringRes override val messageRes: Int = R.string.no_internet_connection,
//            val errorMessage: String? = null
//        ) : Network(messageRes, errorMessage)
    }

    // Server-related exceptions
    sealed class Server(@StringRes messageRes: Int? = null, message: String?) :
        HandleExceptions(messageRes, message) {

        /**
         * Represents server exceptions that can be retried. (e.g.,503 -> ServiceUnavailable, 504 -> GatewayTimeout)
         * This exception is retryable, and the retry delay is set to 5000ms.
         */
        data class RetryableServerException(
            @StringRes override val messageRes: Int = R.string.error_retryable_server,
            val errorMessage: String? = null,
        ) : Server(messageRes, errorMessage), Retryable {
            override fun getRetryDelay() = 5000L
        }

        /**
         * Represents server exceptions that cannot be retried.
         */
        data class NonRetryableServerException(
            @StringRes override val messageRes: Int = R.string.error_non_retryable_server,
            val errorMessage: String? = null
        ) : Server(messageRes, errorMessage)
    }

    // Client-related exceptions
    sealed class Client(@StringRes messageRes: Int? = null, message: String?) :
        HandleExceptions(messageRes, message) {

        /**
         * Represents an unauthorized access exception (HTTP 401).
         */
        data class UnauthorizedAccess(
            @StringRes override val messageRes: Int = R.string.error_unauthorized_access,
            val errorMessage: String? = null
        ) : Client(messageRes, errorMessage)

        /**
         * Represents a not found exception (HTTP 404).
         */
        data class NotFound(
            @StringRes override val messageRes: Int = R.string.error_not_found,
            val errorMessage: String? = null
        ) : Client(messageRes, errorMessage)

        /**
         * Represents a validation error in the response (e.g., missing or invalid fields).
         */
        data class ResponseValidation(
            val fieldErrors: Map<String, String> = emptyMap(),
            val errorMessage: String? = null
        ) : Client(null, errorMessage)
    }

    // Local-related exceptions
    sealed class Local(@StringRes messageRes: Int? = null, message: String?) :
        HandleExceptions(messageRes, message) {

        /**
         * Represents an I/O processing error.
         */
        data class IOProcess(
            @StringRes override val messageRes: Int? = null,
            val errorMessage: String? = null
        ) : Local(messageRes, errorMessage)

        /**
         * Represents a validation error in a local request.
         */
        data class RequestValidation(
            val fieldErrors: Map<String, Int> = emptyMap(),
            val errorMessage: String? = null
        ) : Client(null, errorMessage)
    }


    /**
     * Represents an exception that has reached the maximum retry limit.
     * This exception is used to indicate that no further retries should be attempted.
     */
    class MaxRetryReachedException(
        val errorMessage: String? = null
    ) : HandleExceptions(null, errorMessage), Retryable {
        override fun getRetryDelay(): Long = 0L // Or some appropriate value
    }

    /**
     * Represents an unexpected HTTP exception with an unknown status code.
     */
    data class UnexpectedHttpException(
        val httpErrorCode: Int,
        val errorMessage: String? = null
    ) : HandleExceptions(null, errorMessage)

    /**
     * Represents an unclassified exception.
     */
    data class UnknownException(
        val errorMessage: String? = null
    ) : HandleExceptions(null, errorMessage)

    /**
     * Helper method to check if the exception is retryable.
     *
     * @return True if the exception is retryable, false otherwise.
     */
    fun isRetryable() = this is Retryable
}