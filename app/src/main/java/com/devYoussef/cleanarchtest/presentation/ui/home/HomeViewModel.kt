package com.devYoussef.cleanarchtest.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devYoussef.cleanarchtest.common.model.state.Status
import com.devYoussef.cleanarchtest.domain.model.MovieResponse
import com.devYoussef.cleanarchtest.domain.usecases.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow<Status<MovieResponse>>(Status.Loading(true))
    val homeState = _homeState.asStateFlow()

    init {
        fetchHomeMovies()
    }

    private fun fetchHomeMovies() {
        viewModelScope.launch {
            homeUseCase.invoke().collect { result ->
                _homeState.value = result
            }
        }
    }

}