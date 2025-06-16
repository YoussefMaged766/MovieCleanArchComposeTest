package com.devYoussef.cleanarchtest.common.custom_components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.devYoussef.cleanarchtest.R
import com.devYoussef.cleanarchtest.common.model.exception.HandleExceptions
import com.devYoussef.cleanarchtest.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieTopAppBar(
    modifier: Modifier = Modifier,
    mainNavController: NavController,
    title: @Composable () -> Unit
) {

    CenterAlignedTopAppBar(
        modifier = modifier,
        title = title,
        navigationIcon = {
            NavigationIcon(mainNavController = mainNavController)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.background),
            titleContentColor = Color.White
        ),
    )

}

@Composable
fun NavigationIcon(modifier: Modifier = Modifier, mainNavController: NavController) {

    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .border(
                width = 1.dp, brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xff3F3F3F),
                        Color.Transparent,
                        Color(0xff3F3F3F)
                    )
                ), shape = CircleShape
            )
            .background(color = Color(0xff222222))
            .clickable(
                onClick = {
                    mainNavController.popBackStack()
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_arrow),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .padding(12.dp)
        )
    }

}



@Composable
@Preview
fun MovieTopAppBarPreview() {
    MovieTopAppBar(
        mainNavController = rememberNavController(),
        title = {
            Text(
                text = "Movie Title",
                color = Color.White
            )
        }
    )
}