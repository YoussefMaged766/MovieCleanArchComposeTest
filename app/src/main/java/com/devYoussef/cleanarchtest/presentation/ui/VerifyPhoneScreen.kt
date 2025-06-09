package com.devYoussef.cleanarchtest.presentation.ui

import android.util.Log
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devYoussef.cleanarchtest.R
import com.devYoussef.cleanarchtest.common.custom_components.MovieTopAppBar
import com.devYoussef.cleanarchtest.navigation.Screens

@Composable
fun VerifyPhoneScreen(
    modifier: Modifier = Modifier,
    mainNavController: NavController,
    phone: String
) {
    var otpState by remember { mutableStateOf(TextFieldValue(text = "")) }
    val focusStates = remember { mutableStateListOf(false, false, false, false, false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    var selectedIndex by remember { mutableIntStateOf(0) }


    LaunchedEffect(selectedIndex) {
        Log.e("VerifyPhoneScreen: ", selectedIndex.toString())
    }

    Scaffold(
        topBar = {
            MovieTopAppBar(
                title = {
                    Text(
                        text = "Forget password",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(resId = R.font.poppins_regular))
                    )
                },
                mainNavController = mainNavController,
            )
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.background)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Send code to : ",
                modifier = modifier
                    .padding(top = innerPadding.calculateTopPadding())
                    .padding(top = 50.dp),
                fontSize = 16.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(resId = R.font.poppins_regular))
            )

            Text(
                text = phone,
                modifier = modifier.padding(top = 8.dp),
                fontSize = 22.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(resId = R.font.poppins_bold))
            )
            Spacer(modifier = Modifier.weight(0.3f))

            BasicTextField(
                value = otpState,
                onValueChange = {
                    if (it.text.length <= 5 && it.text.all { char -> char.isDigit() }) {
                        otpState = it
                        selectedIndex = maxOf(it.text.length - 1, 0)
                        if (it.text.length == 5) {
                            keyboardController?.hide()
                            mainNavController.popBackStack(
                                route = Screens.SignInScreen,
                                inclusive = false
                            )
                        }
                    }
                },
                modifier = modifier,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                maxLines = 1,
                decorationBox = {
                    Row(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        repeat(5) { index ->
                            val number = when {
                                index >= otpState.text.length -> ""
                                else -> otpState.text[index]
                            }
                            val isFilled = index < otpState.text.length
                            // Animate scale based on whether the digit is filled
                            val scale by animateFloatAsState(
                                targetValue = if (isFilled) 1.5f else 1f,
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = FastOutLinearInEasing
                                ),
                                label = "DigitScale"
                            )
                            Box(
                                modifier = modifier
                                    .size(60.dp)

                                    .border(
                                        width = 2.dp,
                                        // that for make filed the is filled that only with border orange
                                        color = if (index < otpState.text.length) colorResource(
                                            R.color.orange
                                        ) else Color(
                                            0xFF6E6E6E
                                        ),
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .align(Alignment.CenterVertically)
                                    .onFocusChanged {
                                        focusStates[selectedIndex] = it.isFocused
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = number.toString(),
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    modifier = modifier.graphicsLayer {
                                        scaleX = scale
                                        scaleY = scale
                                    }
                                )
                            }

                        }

                    }
                }

            )
            Button(
                onClick = { /* handle submit */ },
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = innerPadding.calculateBottomPadding()
                    )
                    .padding(bottom = 24.dp)
                    .height(68.dp)
                    .fillMaxWidth(),
                enabled = otpState.text.length == 5,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.orange),
                    disabledContainerColor = colorResource(R.color.grey)
                )
            ) {
                Text(text = "Done", fontFamily = FontFamily(Font(resId = R.font.poppins_regular)))
            }


            Spacer(modifier = Modifier.weight(1f))
        }


    }
}