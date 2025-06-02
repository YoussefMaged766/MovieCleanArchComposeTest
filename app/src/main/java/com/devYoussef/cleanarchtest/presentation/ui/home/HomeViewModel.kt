package com.devYoussef.cleanarchtest.presentation.ui.home


import com.devYoussef.cleanarchtest.common.network.NetworkMonitor
import com.devYoussef.cleanarchtest.domain.model.MovieResponse
import com.devYoussef.cleanarchtest.domain.usecases.HomeUseCase
import com.devYoussef.cleanarchtest.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    networkMonitor: NetworkMonitor
) : BaseViewModel<MovieResponse>(networkMonitor) {

    init {
        fetchHomeMovies()
    }

     fun fetchHomeMovies() {
        launchWithStatusHandling {
            homeUseCase.invoke()
        }
    }

}