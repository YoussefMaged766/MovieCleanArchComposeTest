package com.devYoussef.cleanarchtest.presentation.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devYoussef.cleanarchtest.R
import com.devYoussef.cleanarchtest.common.custom_components.MovieTopAppBar

@Composable
fun VerifyPhoneScreen(
    modifier: Modifier = Modifier,
    mainNavController: NavController,
    phone: String
) {
    var otpState by remember { mutableStateOf(TextFieldValue(text = "")) }
    val focusStates = remember { mutableStateListOf(false, false, false, false, false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    var selectedIndex by  remember { mutableIntStateOf(0) }

    LaunchedEffect(selectedIndex) {
        Log.e( "VerifyPhoneScreen: ", selectedIndex.toString())
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
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.background))
        ) {
            BasicTextField(
                value = otpState,
                onValueChange = {
                    if (it.text.length <= 5) {
                        otpState = it
                        selectedIndex =  maxOf(it.text.length - 1, 0)
                        if (it.text.length == 5) {
                            keyboardController?.hide()
                        }
                    }
                },
                modifier = modifier
                    .align(
                        alignment = Alignment.Center
                    ),
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
                            Box(
                                modifier = modifier
                                    .border(
                                        width = 2.dp,
                                        color = if (index == selectedIndex) colorResource(R.color.orange) else Color(
                                            0xFF6E6E6E
                                        ),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .padding(20.dp)
                                    .onFocusChanged {
                                        focusStates[selectedIndex] = it.isFocused
                                    }
                            ) {
                                Text(
                                    text = number.toString(),
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            }

                        }

                    }
                }

            )
        }

    }
}