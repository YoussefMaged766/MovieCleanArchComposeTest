package com.devYoussef.cleanarchtest.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun VerifyPhoneScreen(modifier: Modifier = Modifier, mainNavController: NavController) {

    Scaffold() { innerPadding ->
        Row {
            repeat(4) { index ->
                Text(
                    "alo",
                    modifier = modifier.padding(
                        top = innerPadding.calculateTopPadding(),
                        end = 10.dp
                    )
                )
            }
        }
    }
}