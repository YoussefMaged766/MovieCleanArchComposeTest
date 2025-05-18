package com.devYoussef.cleanarchtest.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devYoussef.cleanarchtest.R
import com.devYoussef.cleanarchtest.navigation.Screens

@Composable
fun SignInScreen(modifier: Modifier = Modifier, mainNavController: NavController) {
    var emailValueState by remember { mutableStateOf(TextFieldValue("")) }
    var isFocusedEmail by remember { mutableStateOf(false) }
    val focusRequesterEmail = remember { FocusRequester() }

    var isFocusedPassword by remember { mutableStateOf(false) }
    val focusRequesterPassword = remember { FocusRequester() }
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordValueState by remember { mutableStateOf(TextFieldValue("")) }
    val keyboardController = LocalSoftwareKeyboardController.current

    val context = LocalContext.current

    Scaffold() { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.background))
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
                    fontSize = 32.sp,
                    fontFamily = FontFamily(
                        Font(resId = R.font.poppins_medium)
                    )
                )
                Text(
                    text = "Please sign in to your account",
                    modifier = Modifier.padding(top = 12.dp),
                    color = Color(0xFF898989),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(
                        Font(resId = R.font.poppins_regular)
                    )
                )
            }

            // email text field
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
                    tint = if (isFocusedEmail || emailValueState.text.isNotEmpty()) Color.White else colorResource(
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
                            color = colorResource(id = R.color.grey),
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
                            IconButton(
                                onClick = { emailValueState = TextFieldValue("") },
                                content = {
                                    Icon(
                                        Icons.Filled.Clear,
                                        contentDescription = null,
                                        tint = if (isFocusedEmail || emailValueState.text.isNotEmpty()) Color.White else colorResource(
                                            id = R.color.grey
                                        )
                                    )
                                }
                            )
                        }
                    }
                )
            }

            // password text field
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 24.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color(0xFF2B2B2B))
                    .height(IntrinsicSize.Min) // Makes Row height wrap its content
                    .fillMaxWidth()

            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_password),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp, vertical = 20.dp)
                        .align(Alignment.CenterVertically),
                    tint = if (isFocusedPassword || passwordValueState.text.isNotEmpty()) Color.White else colorResource(
                        R.color.grey
                    )
                )
                VerticalDivider(
                    modifier = Modifier
                        .fillMaxHeight() // Now it fills height of Row, which is based on content
                        .width(1.dp)
                        .padding(vertical = 20.dp),
                    color = colorResource(R.color.grey)
                )

                TextField(
                    value = passwordValueState,
                    onValueChange = { passwordValueState = it },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth()
                        .focusRequester(focusRequesterPassword)
                        .onFocusChanged { focusState ->
                            isFocusedPassword = focusState.isFocused
                        },
                    placeholder = {
                        Text(
                            text = "Password",
                            color = colorResource(R.color.grey),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(
                                Font(resId = R.font.poppins_regular)
                            )
                        )
                    },
                    singleLine = true,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
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
                        IconButton(
                            onClick = { passwordVisible = !passwordVisible },
                            content = {
                                Icon(
                                    if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                    contentDescription = null,
                                    tint = if (isFocusedPassword || passwordValueState.text.isNotEmpty()) Color.White else colorResource(
                                        id = R.color.grey
                                    )
                                )
                            }
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Forgot password?",
                modifier = Modifier
                    .padding(end = 20.dp)
                    .align(Alignment.End)
                    .clickable(
                        onClick = {
                            mainNavController.navigate(Screens.ForgetPassword)
                        }
                    ),
                color = Color(0xFF6C6C6C),
                fontSize = 14.sp,
                fontFamily = FontFamily(
                    Font(resId = R.font.poppins_regular)
                )
            )

            Spacer(modifier = Modifier.height(80.dp))

            Button(
                onClick = {
                   mainNavController.navigate(Screens.MainScreen)
                },
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .height(68.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.orange),
                    contentColor = Color.White
                ),
            ) {
                Text(
                    text = "Sign In",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(
                        Font(resId = R.font.poppins_medium)
                    )
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .height(68.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_google),
                    contentDescription = "google",
                    modifier = Modifier.size(44.dp),
                    tint = Color.Unspecified
                )
                Text(
                    text = "Sign In with Google",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(
                        Font(resId = R.font.poppins_medium)
                    )
                )
            }
            val annotatedString = buildAnnotatedString {

                // Mark the "Sign up" part as clickable
                withStyle(
                    style = SpanStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(
                            Font(resId = R.font.poppins_regular)
                        )
                    )
                ) {
                    append("Don't have an account? ")
                }
                pushStringAnnotation(tag = "SIGN_UP", annotation = "sign_up")
                withStyle(
                    style = SpanStyle(
                        color = colorResource(R.color.orange),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(
                            Font(resId = R.font.poppins_regular)
                        )
                    )
                ) {
                    append("Sign Up")
                }
                pop()
            }
            var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

            Text(
                text = annotatedString,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 24.dp)
                    .pointerInput(Unit) {
                        detectTapGestures { offset ->
                            textLayoutResult?.let {
                                val offset = it.getOffsetForPosition(offset)
                                annotatedString.getStringAnnotations(
                                    tag = "SIGN_UP",
                                    start = offset,
                                    end = offset
                                ).firstOrNull()?.let {
                                    mainNavController.navigate(Screens.SignUpScreen)
                                }
                            }
                        }
                    },
                onTextLayout = { textLayoutResult = it }
            )
        }
    }
}