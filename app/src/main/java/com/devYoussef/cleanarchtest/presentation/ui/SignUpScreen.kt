package com.devYoussef.cleanarchtest.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devYoussef.cleanarchtest.R
import com.devYoussef.cleanarchtest.common.custom_components.MovieTopAppBar

@Composable
fun SignUpScreen(modifier: Modifier = Modifier, mainNavController: NavController) {

    Scaffold(
        topBar = {
            MovieTopAppBar(
                title = {
                    Text(
                        text = "Sign Up",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                },
                mainNavController = mainNavController,


            )
        }
    ) { innerPadding ->

        Text(
            text = "alo",
            modifier = modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.status_bar))
                .padding(innerPadding)
        )

    }


}

