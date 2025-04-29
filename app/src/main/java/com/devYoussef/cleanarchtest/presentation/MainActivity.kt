package com.devYoussef.cleanarchtest.presentation

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.devYoussef.cleanarchtest.R
import com.devYoussef.cleanarchtest.navigation.App
import com.devYoussef.cleanarchtest.presentation.theme.CleanArchTestTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = getColor(R.color.status_bar),
                darkScrim = getColor(R.color.status_bar),
            ),
            navigationBarStyle = SystemBarStyle.light(
                scrim = getColor(R.color.status_bar),
                darkScrim = getColor(R.color.status_bar),
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchTestTheme {
                App(rememberNavController())
            }
        }
    }
}

