package com.devYoussef.cleanarchtest.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devYoussef.cleanarchtest.R

@Composable
fun GetStartedScreen(modifier: Modifier = Modifier, mainNavController: NavController) {
    Scaffold{ innerPadding->

        Box(modifier = Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(R.drawable.img_get_started),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                contentDescription = null
            )

            OverLay(modifier = Modifier.fillMaxSize())


            Box(
                modifier = Modifier.padding(innerPadding),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_logo_text),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color(0xffFF8800)),
                        modifier = modifier
                            .size(width = 160.dp, height = 40.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                    )

                    Spacer(modifier = modifier.height(18.dp))

                    Text(
                        text = "watch your favorite movie or serise on\n" +
                                "only one platform. you can waych it\n" +
                                "anytime and anywhere",
                        modifier = modifier.fillMaxWidth(),
                        color = Color.White,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(resId = R.font.poppins_regular))
                    )

                    Spacer(modifier = modifier.height(48.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        ElevatedButton(
                            onClick = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.5f)
                                .padding(start = 20.dp, end = 10.dp),
                            contentPadding = PaddingValues(vertical = 15.dp),
                            shape = RoundedCornerShape(22.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFF8800),
                                contentColor = Color.White
                            ),
                        ) {
                            Text(text = "Sign in")
                        }

                        OutlinedButton(
                            onClick = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.5f)
                                .padding(end = 20.dp, start = 10.dp),
                            border = BorderStroke(width = 1.dp, color = Color.White),
                            shape = RoundedCornerShape(22.dp),
                            contentPadding = PaddingValues(vertical = 15.dp)

                        ) {
                            Text(text = "Sign Up", color = Color.White)
                        }
                    }
                    Spacer(modifier = modifier.height(20.dp))
                    Text(
                        text = "Continue as a Guest",
                        modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                        color = Color.White,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(resId = R.font.poppins_regular)),
                        textDecoration = TextDecoration.Underline
                    )
                }

            }
        }
    }


}

@Composable
fun OverLay(modifier: Modifier = Modifier) {
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
                        Color.Transparent,
                        Color(0xFF151515),
                    )
                )
            )
    )
}