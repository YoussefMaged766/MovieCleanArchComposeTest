package com.devYoussef.cleanarchtest.navigation

import kotlinx.serialization.Serializable

sealed class Screens {

    @Serializable
    data object SplashScreen : Screens()

    @Serializable
    data object IntroScreen : Screens()
}