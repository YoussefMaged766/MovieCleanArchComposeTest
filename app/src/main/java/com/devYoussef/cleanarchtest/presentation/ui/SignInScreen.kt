package com.devYoussef.cleanarchtest.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devYoussef.cleanarchtest.R

@Composable
fun SignInScreen(modifier: Modifier = Modifier, mainNavController: NavController) {
    var emailValueState by remember { mutableStateOf(TextFieldValue("")) }
    var isFocusedEmail by remember { mutableStateOf(false) }
    val focusRequesterEmail = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold() { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFF151515))
                .padding(innerPadding),

            ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp)

            ) {
                Text(
                    text = "Welcome back!",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontFamily = FontFamily(
                        Font(resId = R.font.poppins_medium)
                    )
                )
                Text(
                    text = "Please sign in to your account",
                    color = Color(0xFF898989),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(
                        Font(resId = R.font.poppins_regular)
                    )
                )
            }

            Row(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 100.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color(0xFF2B2B2B))
                    .height(IntrinsicSize.Min) // Makes Row height wrap its content
                    .fillMaxWidth()

            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_user),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp, vertical = 20.dp)
                        .align(Alignment.CenterVertically),
                    tint = if (isFocusedEmail) Color.White else Color(0xFF828282)
                )
                VerticalDivider(
                    modifier = Modifier
                        .fillMaxHeight() // Now it fills height of Row, which is based on content
                        .width(1.dp)
                        .padding(vertical = 20.dp),
                    color = Color(0xFF828282)
                )

                TextField(
                    value = emailValueState,
                    onValueChange = { emailValueState = it },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth()
                        .focusRequester(focusRequesterEmail)
                        .onFocusChanged { focusState ->
                            isFocusedEmail = focusState.isFocused
                        },
                    placeholder = {
                        Text(
                            text = "Email",
                            color = Color(0xFF828282),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(
                                Font(resId = R.font.poppins_regular)
                            )
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),

                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(
                            Font(resId = R.font.poppins_regular)
                        )
                    ),
                    trailingIcon = {
                        if (emailValueState.text.isNotEmpty()) {
                            IconButton(onClick = { emailValueState  = TextFieldValue("")}, content = {
                                Icon(
                                    Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            })
                        }
                    }
                )
            }
        }
    }
}