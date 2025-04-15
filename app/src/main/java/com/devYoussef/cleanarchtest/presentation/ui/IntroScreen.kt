package com.devYoussef.cleanarchtest.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devYoussef.cleanarchtest.R

@Composable
fun IntroScreen(modifier: Modifier = Modifier, mainNavController: NavController) {

    Box(modifier = modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.img_intro),
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize(),
            contentDescription = null
        )

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0x4DFF8800),
                            Color(0xFF151515),
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent,
                            Color(0xFF151515),
                        )
                    )
                )
        )



        Column(modifier = modifier.fillMaxSize()) {
            Box(
                modifier = modifier.weight(0.3f),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "AFLAMY.CO",
                    fontFamily = FontFamily.Monospace,
                    modifier = modifier
                        .padding(top = 18.dp),
                    color = Color.White,
                    style = MaterialTheme.typography.headlineLarge,
                    fontSize = 48.sp

                )
            }

        }


    }

}