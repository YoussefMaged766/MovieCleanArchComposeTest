package com.devYoussef.cleanarchtest.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devYoussef.cleanarchtest.R
import com.devYoussef.cleanarchtest.common.custom_components.MovieTopAppBar

@Composable
fun VerifyPhoneScreen(
    modifier: Modifier = Modifier,
    mainNavController: NavController,
    phone: String
) {
    Scaffold(
        topBar = {
            MovieTopAppBar(
                title = {
                    Text(
                        text = "Forget password",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(resId = R.font.poppins_regular))
                    )
                },
                mainNavController = mainNavController,
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.background))
        ) {
            Row {
                repeat(4) { index ->
                    Text(
                        phone,
                        modifier = modifier.padding(
                            top = innerPadding.calculateTopPadding(),
                            end = 10.dp
                        )
                    )
                }
            }
        }

    }
}