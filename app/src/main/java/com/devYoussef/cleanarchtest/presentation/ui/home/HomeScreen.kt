package com.devYoussef.cleanarchtest.presentation.ui.home


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devYoussef.cleanarchtest.R
import com.devYoussef.cleanarchtest.common.model.state.Status
import com.devYoussef.cleanarchtest.presentation.ui.base.BaseScreenScaffold

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    mainNavController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    val exception = (state.value as? Status.Failure)?.exception

    BaseScreenScaffold(
        isOnline = viewModel.isOnline,
        exception = exception,
        onRetry = { viewModel.fetchHomeMovies() },

        ) { snackbarHostState ->
        when (val status = state.value) {
            is Status.Loading -> {
                // Show loading UI
            }

            is Status.Success -> {
                Log.e( "HomeScreen: ", status.data.toString())
                // Show data UI
            }

            is Status.Failure -> {
                // Optionally show fallback UI (error overlay, etc.)
            }
        }
    }

    // Handle the data received from the ViewModel


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