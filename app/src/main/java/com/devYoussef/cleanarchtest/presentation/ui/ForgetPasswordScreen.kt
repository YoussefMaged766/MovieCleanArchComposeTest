package com.devYoussef.cleanarchtest.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devYoussef.cleanarchtest.R
import com.devYoussef.cleanarchtest.common.custom_components.MovieTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ForgetPasswordScreen(modifier: Modifier = Modifier, onNavigateToVerifyPhone : (String) -> Unit = {} , mainNavController: NavController) {

    var phoneValueState by remember { mutableStateOf(TextFieldValue(text = "")) }
    var isFocusedPhone by remember { mutableStateOf(false) }
    val focusRequesterPhone = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    var phoneError by remember { mutableStateOf(false) }


    fun phoneValidation(phone: String): Boolean {
        return phone.length == 11 && phone.all { it.isDigit() } && phone.startsWith("0") && phone.isNotEmpty()
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

            // phone text field
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color(0xFF2B2B2B))
                    .height(IntrinsicSize.Min) // Makes Row height wrap its content
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp, vertical = 20.dp)
                        .align(Alignment.CenterVertically),
                    tint = if (isFocusedPhone || phoneValueState.text.isNotEmpty()) Color.White else colorResource(
                        id = R.color.grey
                    )
                )
                VerticalDivider(
                    modifier = Modifier
                        .fillMaxHeight() // Now it fills height of Row, which is based on content
                        .width(1.dp)
                        .padding(vertical = 20.dp),
                    color = colorResource(id = R.color.grey)
                )
                TextField(
                    value = phoneValueState,
                    onValueChange = {
                        if (it.text.length <= 11) {
                            phoneValueState = it
                            phoneError = !phoneValidation(it.text)
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth()
                        .focusRequester(focusRequesterPhone)
                        .onFocusChanged { focusState ->
                            isFocusedPhone = focusState.isFocused
                        },
                    placeholder = {
                        Text(
                            text = "Phone number",
                            color = colorResource(id = R.color.grey),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(
                                Font(resId = R.font.poppins_regular)
                            )
                        )
                    },
                    isError = phoneError,
                    singleLine = true,
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),

                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        errorIndicatorColor = Color.Red,
                        errorContainerColor = Color.Transparent,
                    ),

                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(
                            Font(resId = R.font.poppins_regular)
                        )
                    ),
                    trailingIcon = {
                        if (phoneValueState.text.isNotEmpty()) {
                            IconButton(
                                onClick = { phoneValueState = TextFieldValue("") },
                                content = {
                                    Icon(
                                        Icons.Filled.Clear,
                                        contentDescription = null,
                                        tint = if (isFocusedPhone || phoneValueState.text.isNotEmpty()) Color.White else colorResource(
                                            id = R.color.grey
                                        )
                                    )
                                }
                            )
                        }
                    }
                )
            }


            // send button


            Button(
                onClick = {
                    if (phoneValidation(phoneValueState.text))
                        onNavigateToVerifyPhone(phoneValueState.text)
                    else
                        phoneError = true
                },
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = innerPadding.calculateBottomPadding()
                    )
                    .padding(bottom = 24.dp)
                    .align(Alignment.BottomCenter)
                    .height(68.dp)
                    .fillMaxWidth(),
                enabled = !phoneError,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.orange),
                    disabledContainerColor = colorResource(R.color.grey)
                )

            ) {
                Text(
                    text = "Send Code",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(
                        Font(resId = R.font.poppins_medium)
                    )
                )
            }
        }
    }
}