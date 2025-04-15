package com.devYoussef.cleanarchtest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devYoussef.cleanarchtest.presentation.ui.SplashScreen

@Composable
fun App(mainNavController: NavHostController) {
    NavHost(
        navController = mainNavController,
        startDestination = Screens.SplashScreen
    ) {
        composable<Screens.SplashScreen> {
            SplashScreen()
        }
        composable<Screens.IntroScreen> {

        }
    }


}