package com.devYoussef.cleanarchtest.presentation.ui.main

import android.util.Log
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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devYoussef.cleanarchtest.R
import com.devYoussef.cleanarchtest.presentation.navigation.Screens
import com.devYoussef.cleanarchtest.presentation.ui.ExploreScreen
import com.devYoussef.cleanarchtest.presentation.ui.home.HomeScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier, mainNavController: NavController) {
    val navController = rememberNavController()
    val navItems = listOf<MainNavigationData>(
        MainNavigationData(
            title = "Home",
            icon = R.drawable.ic_home,
            route = Screens.HomeScreen
        ),
        MainNavigationData(
            title = "Explore",
            icon = R.drawable.ic_explore,
            route = Screens.ExploreScreen
        ),
        MainNavigationData(
            title = "Saved",
            icon = R.drawable.ic_saved,
            route = Screens.HomeScreen
        ),

        MainNavigationData(
            title = "Setting",
            icon = R.drawable.ic_setting,
            route = Screens.HomeScreen
        )
    )


    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val snackbarHostState = remember { SnackbarHostState() }

    val selectedItem = remember(currentRoute) {
        navItems.indexOfFirst { it.route::class.qualifiedName == currentRoute }
            .takeIf { it != -1 } ?: 0
    }

    val lifecycleOwner = LocalLifecycleOwner.current


//    BackHandler(enabled = selectedItem != 0) {
//        selectedItem = 0
//    }

//    LaunchedEffect(lifecycleOwner) {
//        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
//            Log.e("MainScreen: ","resumed" )
//        }
//    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        bottomBar = {
            Column {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.DarkGray // Customize this color as needed
                )

                NavigationBar(
                    windowInsets = NavigationBarDefaults.windowInsets,
                    containerColor = colorResource(R.color.background),
                ) {
                    navItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = index == selectedItem,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(Screens.HomeScreen) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xffCC6D00),
                                unselectedIconColor = Color(0xff8c8c8c),
                                selectedTextColor = Color(0xffCC6D00),
                                unselectedTextColor = Color(0xff8c8c8c),
                                indicatorColor = Color.Transparent
                            ),
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = item.title,
                                    tint = if (index == selectedItem) Color(0xffCC6D00) else Color(
                                        0xff8c8c8c
                                    ),
                                )
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    color = if (index == selectedItem) Color(0xffCC6D00) else Color(
                                        0xff8c8c8c
                                    )
                                )
                            }
                        )
                    }
                }
            }
        },
        modifier = modifier.background(colorResource(R.color.background))
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screens.HomeScreen
        ) {
            composable<Screens.HomeScreen> {
                HomeScreen(mainNavController = navController , snackbarHostState = snackbarHostState ,
                    innerPadding = innerPadding
                )
            }

            composable<Screens.ExploreScreen> {
                ExploreScreen(mainNavController = navController)
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

//HomeNavItem(
//                            data = item,
//                            selected = index == selectedItem,
//                            onClick = {
//                                navController.navigate(item.route) {
//                                    // Avoid multiple copies of the same destination
//                                    popUpTo(Screens.HomeScreen) {
//                                        saveState = true
//                                    }
//                                    launchSingleTop = true
//                                    restoreState = true
//                                }
//
//                            }
//                        )

