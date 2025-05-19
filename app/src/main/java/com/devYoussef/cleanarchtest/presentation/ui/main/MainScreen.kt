package com.devYoussef.cleanarchtest.presentation.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devYoussef.cleanarchtest.R
import com.devYoussef.cleanarchtest.navigation.Screens
import com.devYoussef.cleanarchtest.presentation.ui.ExploreScreen
import com.devYoussef.cleanarchtest.presentation.ui.HomeScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier, mainNavController: NavController) {

    val navItems = listOf<MainNavigationData>(
        MainNavigationData(
            title = "Home",
            icon = R.drawable.ic_home,
            route = Screens.MainScreen,
            screen = { HomeScreen(mainNavController = mainNavController) }
        ),
        MainNavigationData(
            title = "Explore",
            icon = R.drawable.ic_explore,
            route = Screens.MainScreen,
            screen = { ExploreScreen(mainNavController = mainNavController) }
        ),
        MainNavigationData(
            title = "Saved",
            icon = R.drawable.ic_saved,
            route = Screens.MainScreen,
            screen = { /* TODO: Implement Profile Screen */ }
        ),

        MainNavigationData(
            title = "Setting",
            icon = R.drawable.ic_setting,
            route = Screens.MainScreen,
            screen = { /* TODO: Implement Profile Screen */ }
        )
    )

    var selectedItem by remember { mutableIntStateOf(0) }

    BackHandler(enabled = selectedItem != 0) {
        selectedItem = 0
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(modifier = modifier, containerColor = colorResource(R.color.background)) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 25.dp)
                        .clip(RoundedCornerShape(90.dp))
                        .background(color = colorResource(R.color.orange))
                ) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        navItems.forEachIndexed { index, item ->
                            HomeNavItem(
                                data = item,
                                selected = selectedItem == index,
                                onClick = {
                                    selectedItem = index
                                }
                            )
                        }
                    }
                }
            }
        },
        modifier = modifier.background(colorResource(R.color.background))
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(colorResource(R.color.background))
        ) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                navItems[selectedItem].screen(mainNavController)
            }

        }
    }
}

@Composable
fun HomeNavItem(
    data: MainNavigationData,
    selected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (selected) Color(0xffCC6D00) else Color.Transparent,
        animationSpec = tween(durationMillis = 300)
    )

    val scale by animateFloatAsState(
        targetValue = if (selected) 1.1f else 1.0f,
        animationSpec = tween(durationMillis = 300)
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(90.dp))
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = data.icon),
                contentDescription = data.title,
                tint = Color.White
            )
            AnimatedVisibility(
                visible = selected,
                enter = fadeIn(animationSpec = tween(300)) + expandHorizontally(),
                exit = fadeOut(animationSpec = tween(300)) + shrinkHorizontally()
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = data.title, color = Color.White)
            }
        }
    }
}