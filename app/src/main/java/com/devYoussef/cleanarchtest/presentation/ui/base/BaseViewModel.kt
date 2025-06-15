package com.devYoussef.cleanarchtest.presentation.ui.base


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devYoussef.cleanarchtest.common.network.NetworkMonitor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    internal val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _isOnline = MutableStateFlow(true)
    val isOnline: StateFlow<Boolean> = _isOnline.asStateFlow()

    init {
        viewModelScope.launch {
            networkMonitor.isOnline.collect {
                _isOnline.value = it
            }
        }
    }
}
