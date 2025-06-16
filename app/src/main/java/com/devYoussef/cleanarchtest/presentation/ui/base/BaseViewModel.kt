package com.devYoussef.cleanarchtest.presentation.ui.base


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devYoussef.cleanarchtest.common.model.state.UiEffect
import com.devYoussef.cleanarchtest.common.network.NetworkMonitor
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    internal val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _isOnline = MutableStateFlow(true)
    val isOnline: StateFlow<Boolean> = _isOnline.asStateFlow()

    private val _uiEffect = MutableSharedFlow<UiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    protected suspend fun emitEffect(effect: UiEffect) {
        _uiEffect.emit(effect)
    }

    init {
        viewModelScope.launch {
            networkMonitor.isOnline.collect {
                _isOnline.value = it
            }
        }
    }



}
