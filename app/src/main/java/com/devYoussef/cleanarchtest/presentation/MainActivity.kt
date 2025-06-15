package com.devYoussef.cleanarchtest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.devYoussef.cleanarchtest.R
import com.devYoussef.cleanarchtest.presentation.navigation.App
import com.devYoussef.cleanarchtest.presentation.theme.CleanArchTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

