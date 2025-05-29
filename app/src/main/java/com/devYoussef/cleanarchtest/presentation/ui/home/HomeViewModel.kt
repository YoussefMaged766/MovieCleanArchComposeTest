package com.devYoussef.cleanarchtest.presentation.ui.home

import androidx.lifecycle.ViewModel
import com.devYoussef.cleanarchtest.domain.usecases.HomeUseCase
import jakarta.inject.Inject

class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : ViewModel() {

}