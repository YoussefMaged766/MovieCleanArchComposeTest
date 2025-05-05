package com.devYoussef.cleanarchtest.common.custom_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.devYoussef.cleanarchtest.R

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
            NavigationIcon()
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.status_bar),
            titleContentColor = Color.White
        ),
    )

}

@Composable
fun NavigationIcon(modifier: Modifier = Modifier) {

    Surface(
        modifier = modifier
            .background(
                color = colorResource(R.color.status_bar)
            ),
        shape = CircleShape,
        border = BorderStroke(
            width = 1.dp, brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xff3F3F3F),
                    Color.Transparent,
                    Color(0xff3F3F3F)
                )
            )
        ),
        contentColor = colorResource(R.color.status_bar)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_arrow),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .padding(12.dp)
        )


    }

}

@Composable
@Preview
fun MovieTopAppBarPreview() {
    NavigationIcon()
}