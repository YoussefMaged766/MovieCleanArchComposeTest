package com.devYoussef.cleanarchtest.presentation.ui.home


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.devYoussef.cleanarchtest.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    mainNavController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {


    LaunchedEffect(true) {
        viewModel.homeState.collect {
            Log.e("HomeScreen: ", it.toString())
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.background))
    ) {

        Text(
            text = "home",
            color = Color.White,
            fontSize = 20.sp,
        )
    }

}