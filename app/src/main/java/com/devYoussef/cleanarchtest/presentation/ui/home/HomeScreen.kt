package com.devYoussef.cleanarchtest.presentation.ui.home


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devYoussef.cleanarchtest.R
import com.devYoussef.cleanarchtest.common.model.state.Status
import com.devYoussef.cleanarchtest.presentation.ui.base.ConnectivityWrapper

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    mainNavController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    val exception = (state.value as? Status.Failure)?.exception


    ConnectivityWrapper(
        isOnline = viewModel.isOnline,
        exception = exception,
        onRetry = { viewModel.fetchHomeMovies() },
        effect = viewModel.uiEffect
    ) { snackbarHostState ->


        when (val status = state.value) {
            is Status.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                )
            }

            is Status.Success -> {
                Log.e("HomeScreen: ", status.data.toString())

                Text(
                    text = "home",
                    color = Color.White,
                    fontSize = 20.sp,
                )
            }

            is Status.Failure -> {
                // Optionally show fallback UI (error overlay, etc.)
            }
        }

//        Box(
//            modifier = modifier
//                .fillMaxSize()
//                .background(color = colorResource(R.color.background))
//        ) {
//
//        }


    }


    // Handle the data received from the ViewModel


//    LaunchedEffect(true) {
//        viewModel.state.collect {
//            Log.e("HomeScreen: ", it.toString())
//        }
//    }


}