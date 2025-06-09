package com.devYoussef.cleanarchtest.presentation.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devYoussef.cleanarchtest.common.model.exception.HandleExceptions
import com.devYoussef.cleanarchtest.common.model.state.Status
import com.devYoussef.cleanarchtest.common.network.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel<T>(
    internal val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _state = MutableStateFlow<Status<T>>(Status.Loading(true))
    val state: StateFlow<Status<T>> = _state.asStateFlow()

    val isOnline: StateFlow<Boolean> = networkMonitor.isOnline
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = true
        )


    protected fun setLoading() {
        _state.value = Status.Loading(true)
    }

    protected fun setSuccess(data: T) {
        _state.value = Status.Success(data)
    }

    protected fun setFailure(error: HandleExceptions) {
        _state.value = Status.Failure(error)
    }

    protected fun launchWithStatusHandling(block: suspend () -> Flow<Status<T>>) {
        viewModelScope.launch {
            block.invoke().collect {
                _state.value = it
                Log.e( "launchWithStatusHandling: ",isOnline.value.toString() )
            }
        }
    }
}