package com.devYoussef.cleanarchtest.presentation.ui.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.devYoussef.cleanarchtest.common.custom_components.HandleException
import com.devYoussef.cleanarchtest.common.model.state.Status

@Composable
fun <T> BaseScreen(
    modifier: Modifier = Modifier,
    viewModel: BaseViewModel<T>,
    onRetry: (() -> Unit)? = null,
    onUnauthorized: (() -> Unit)? = null,
    onValidationError: ((Map<String, Int>) -> Unit)? = null,
    content: @Composable (T) -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val isOnline = viewModel.networkMonitor.isOnline.collectAsState(initial = true).value
    val context = LocalContext.current
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (uiState) {
                is Status.Loading -> CircularProgressIndicator()
                is Status.Success -> content((uiState as Status.Success<T>).data)
                is Status.Failure -> {
                    val exception = (uiState as Status.Failure).exception
                    HandleException(
                        exception = exception,
                        snackbarHostState = snackbarHostState,
                        context = context,
                        onRetry = { onRetry?.invoke() },
                        onUnauthorized = onUnauthorized,
                        onValidationError = onValidationError,
                        isOnline = isOnline
                    )
                }
            }
        }
    }
}
