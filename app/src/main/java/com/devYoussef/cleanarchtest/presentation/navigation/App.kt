package com.devYoussef.cleanarchtest.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.devYoussef.cleanarchtest.presentation.ui.ForgetPasswordScreen
import com.devYoussef.cleanarchtest.presentation.ui.GetStartedScreen
import com.devYoussef.cleanarchtest.presentation.ui.main.MainScreen
import com.devYoussef.cleanarchtest.presentation.ui.IntroScreen
import com.devYoussef.cleanarchtest.presentation.ui.login.SignInScreen
import com.devYoussef.cleanarchtest.presentation.ui.SignUpScreen
import com.devYoussef.cleanarchtest.presentation.ui.onboarding.OnBoardingScreen
import com.devYoussef.cleanarchtest.presentation.ui.SplashScreen
import com.devYoussef.cleanarchtest.presentation.ui.VerifyPhoneScreen

@Composable
fun App(mainNavController: NavHostController) {
    NavHost(
        navController = mainNavController,
        startDestination = Screens.SplashScreen,
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

        composable<Screens.SignInScreen> {
            SignInScreen(mainNavController = mainNavController)
        }

        composable<Screens.SignUpScreen> {
            SignUpScreen(mainNavController = mainNavController)
        }

        composable<Screens.ForgetPassword> {
            ForgetPasswordScreen(
                mainNavController = mainNavController,
                onNavigateToVerifyPhone = { phone ->
                    mainNavController.navigate(Screens.VerifyPhoneScreen(phone = phone))
                }
            )
        }

        composable<Screens.VerifyPhoneScreen> { backStackEntry ->
            val args: Screens.VerifyPhoneScreen = backStackEntry.toRoute()
            VerifyPhoneScreen(mainNavController = mainNavController, phone = args.phone)
        }

        composable<Screens.MainScreen> {
            MainScreen(mainNavController = mainNavController)
        }


    }

}
