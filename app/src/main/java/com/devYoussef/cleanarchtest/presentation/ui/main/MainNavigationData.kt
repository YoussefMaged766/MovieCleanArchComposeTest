package com.devYoussef.cleanarchtest.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.devYoussef.cleanarchtest.presentation.navigation.Screens

data class MainNavigationData(
    val title: String,
    val icon: Int,
    val route: Screens
)
