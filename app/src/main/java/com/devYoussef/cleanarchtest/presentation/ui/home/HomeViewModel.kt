package com.devYoussef.cleanarchtest.presentation.ui.home


import androidx.lifecycle.viewModelScope
import com.devYoussef.cleanarchtest.common.model.exception.HandleExceptions
import com.devYoussef.cleanarchtest.common.model.exception.HandleExceptions.MaxRetryReachedException
import com.devYoussef.cleanarchtest.common.model.state.Status
import com.devYoussef.cleanarchtest.common.model.state.UiEffect
import com.devYoussef.cleanarchtest.common.network.NetworkMonitor
import com.devYoussef.cleanarchtest.domain.model.Movie
import com.devYoussef.cleanarchtest.domain.model.MovieResponse
import com.devYoussef.cleanarchtest.domain.usecases.HomeUseCase
import com.devYoussef.cleanarchtest.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    networkMonitor: NetworkMonitor
) : BaseViewModel(networkMonitor) {

    private val _state = MutableStateFlow<Status<MovieResponse>>(Status.Loading(true))
    val state: StateFlow<Status<MovieResponse>> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            networkMonitor.isOnline.collect { isOnline ->
                if (isOnline) {
                    fetchHomeMovies()
                }
            }
        }

    }


    fun fetchHomeMovies() {
        viewModelScope.launch {
            homeUseCase().collect { result ->
                _state.value = result

                // Handle MaxRetryReachedException and show snackbar
                if (result is Status.Failure && result.exception is MaxRetryReachedException) {
                    emitEffect(
                        UiEffect.ShowSnackbar(
                            message = "Failed after multiple attempts. Please try again.",
                            action = "Retry",
                            onAction = { fetchHomeMovies() }
                        )
                    )
                }
            }
        }
    }

}