package com.devYoussef.cleanarchtest.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
