package com.devYoussef.cleanarchtest.presentation.ui.base

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import com.devYoussef.cleanarchtest.R
import com.devYoussef.cleanarchtest.common.model.exception.HandleExceptions
import com.devYoussef.cleanarchtest.common.model.state.UiEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ConnectivityWrapper(
    isOnline: StateFlow<Boolean>,
    exception: HandleExceptions?,
    onRetry: (() -> Unit)? = null,
    onUnauthorized: (() -> Unit)? = null,
    onValidationError: ((Map<String, Int>) -> Unit)? = null,
    effect: SharedFlow<UiEffect>,
    content: @Composable (PaddingValues) -> Unit
) {
    val onlineStatus by isOnline.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        effect.collect { effect ->
            Log.e("HomeScreen: ", effect.toString())
            when (effect) {
                is UiEffect.ShowSnackbar -> {

                    val result = snackbarHostState.showSnackbar(
                        message = effect.message,
                        actionLabel = effect.action
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        effect.onAction?.invoke()
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.background))
        ) {
            content(paddingValues)
            if (!onlineStatus) {
                // Show offline snackbar only once
                LaunchedEffect(Unit) {
                    snackbarHostState.showSnackbar(
                        message = context.getString(R.string.no_internet_connection),
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    // Exception handler logic
    LaunchedEffect(exception, onlineStatus) {
        exception?.let {
            when (it) {
                is HandleExceptions.Network.Timeout,
                is HandleExceptions.Network.UnknownHost,
                is HandleExceptions.Server.RetryableServerException,
                is HandleExceptions.MaxRetryReachedException -> {
                    if (it.isRetryable()) {
                        Log.e(
                            "BaseScreenScaffold",
                            "Online status: $onlineStatus, Exception: $exception"
                        )
                        val result = snackbarHostState.showSnackbar(
                            message = context.getString(it.messageRes ?: R.string.unknown_error),
                            actionLabel = context.getString(R.string.retry)
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            delay(it.getRetryDelay())
                            if (onlineStatus) {
                                onRetry?.invoke()
                            } else {
                                snackbarHostState.showSnackbar(
                                    message = context.getString(R.string.no_internet_connection),
                                    withDismissAction = true
                                )
                            }
                        }
                    }
                }

                is HandleExceptions.Client.NotFound -> {
                    snackbarHostState.showSnackbar(
                        message = context.getString(it.messageRes),
                        withDismissAction = true
                    )
                }

                is HandleExceptions.Client.ResponseValidation -> {
                    val message = it.fieldErrors.entries.joinToString("\n") { entry -> entry.value }
                    snackbarHostState.showSnackbar(message.ifBlank { "Validation error" })
                }

                is HandleExceptions.Client.UnauthorizedAccess -> {
                    val result = snackbarHostState.showSnackbar(
                        message = context.getString(it.messageRes),
                        actionLabel = context.getString(R.string.login),
                        duration = SnackbarDuration.Indefinite
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        onUnauthorized?.invoke()
                    }
                }

                is HandleExceptions.Local.IOProcess -> {
                    Toast.makeText(
                        context,
                        it.messageRes?.let(context::getString)
                            ?: it.errorMessage
                            ?: "IO Error",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is HandleExceptions.Local.RequestValidation -> {
                    onValidationError?.invoke(it.fieldErrors)
                }

                is HandleExceptions.Server.NonRetryableServerException -> {
                    snackbarHostState.showSnackbar(
                        message = context.getString(it.messageRes),
                        withDismissAction = true
                    )
                }

                is HandleExceptions.UnexpectedHttpException -> {
                    snackbarHostState.showSnackbar(
                        message = it.errorMessage ?: "Unexpected HTTP Error",
                        withDismissAction = true
                    )
                }

                is HandleExceptions.UnknownException -> {
                    snackbarHostState.showSnackbar(
                        message = it.errorMessage ?: "Unknown error",
                        withDismissAction = true
                    )
                }
            }
        }
    }
}
