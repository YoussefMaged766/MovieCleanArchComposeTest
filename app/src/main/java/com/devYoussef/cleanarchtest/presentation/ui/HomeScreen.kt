package com.devYoussef.cleanarchtest.presentation.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devYoussef.cleanarchtest.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier , mainNavController: NavController) {

    Box(modifier = modifier
        .fillMaxSize()
        .background(color = colorResource(R.color.background))) {

        Text(
            text = "home",
            color =Color.White ,
            fontSize = 20.sp,
        )
    }

}