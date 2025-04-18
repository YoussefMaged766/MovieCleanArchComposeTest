package com.devYoussef.cleanarchtest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devYoussef.cleanarchtest.presentation.ui.GetStartedScreen
import com.devYoussef.cleanarchtest.presentation.ui.IntroScreen
import com.devYoussef.cleanarchtest.presentation.ui.onboarding.OnBoardingScreen
import com.devYoussef.cleanarchtest.presentation.ui.SplashScreen

@Composable
fun App(mainNavController: NavHostController) {
    NavHost(
        navController = mainNavController,
        startDestination = Screens.SplashScreen
    ) {
        composable<Screens.SplashScreen> {
            SplashScreen(mainNavController = mainNavController)
        }
        composable<Screens.IntroScreen> {
            IntroScreen(mainNavController = mainNavController)
        }

        composable<Screens.OnBoardingScreen> {
            OnBoardingScreen(mainNavController = mainNavController)
        }

        composable<Screens.GetStartedScreen> {
            GetStartedScreen(mainNavController = mainNavController)
        }
    }


}