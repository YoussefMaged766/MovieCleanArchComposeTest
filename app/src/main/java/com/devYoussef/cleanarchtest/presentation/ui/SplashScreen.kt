package com.devYoussef.cleanarchtest.presentation.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devYoussef.cleanarchtest.R
import com.devYoussef.cleanarchtest.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier = Modifier, mainNavController: NavController) {
    var rotateLogo by remember { mutableStateOf(false) }
    val rotateAnim by animateFloatAsState(
        targetValue = if (rotateLogo) 360f else 0f,
        animationSpec = tween(
            durationMillis = 1500
        )
    )

    var alpha = remember { Animatable(0f) }

    LaunchedEffect(true) {
        rotateLogo = true
        delay(1000)
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1500
            )
        )
        delay(500)
        mainNavController.navigate(Screens.IntroScreen) {
            popUpTo(Screens.SplashScreen) {
                inclusive = true
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.img_cover),
            contentDescription = null,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF151515),
                            Color(0xE6151515),
                            Color(0xFF151515)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_logo),
                    contentDescription = null,
                    modifier = modifier
                        .size(132.dp)
                        .rotate(rotateAnim)
                )

                Image(
                    painter = painterResource(R.drawable.img_logo_text),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .alpha(alpha = alpha.value)
                        .padding(top = 12.dp),
                )
            }

        }
    }

}