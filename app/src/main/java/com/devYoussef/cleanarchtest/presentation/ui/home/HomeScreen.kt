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
import com.devYoussef.cleanarchtest.presentation.ui.base.BaseScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    mainNavController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    BaseScreen(
        modifier = modifier,
        viewModel = viewModel,
        onRetry = {viewModel.fetchHomeMovies()},


    ){data->
        Log.e("HomeScreen: ", "Data received: $data")
        // Handle the data received from the ViewModel

    }

//    LaunchedEffect(true) {
//        viewModel.state.collect {
//            Log.e("HomeScreen: ", it.toString())
//        }
//    }

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