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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
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
            route = Screens.HomeScreen,
            screen = { HomeScreen(mainNavController = navController) }
        ),
        MainNavigationData(
            title = "Explore",
            icon = R.drawable.ic_explore,
            route = Screens.ExploreScreen,
            screen = { ExploreScreen(mainNavController = navController) }
        ),
        MainNavigationData(
            title = "Saved",
            icon = R.drawable.ic_saved,
            route = Screens.HomeScreen,
            screen = { /* TODO: Implement Profile Screen */ }
        ),

        MainNavigationData(
            title = "Setting",
            icon = R.drawable.ic_setting,
            route = Screens.HomeScreen,
            screen = { /* TODO: Implement Profile Screen */ }
        )
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route


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

        bottomBar = {
            BottomAppBar(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .clip(RoundedCornerShape(90.dp)),
                containerColor = colorResource(R.color.orange),
                actions = {
                    navItems.forEachIndexed { index, item ->
                        HomeNavItem(
                            data = item,
                            selected = index == selectedItem,
                            onClick = {
                                navController.navigate(item.route) {
                                    // Avoid multiple copies of the same destination
                                    popUpTo(Screens.HomeScreen) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }

                            }
                        )
                    }
                },
            )

        },
        modifier = modifier.background(colorResource(R.color.background))
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screens.HomeScreen
        ) {
            composable<Screens.HomeScreen> {
                HomeScreen(mainNavController = navController)
            }

            composable<Screens.ExploreScreen> {
                ExploreScreen(mainNavController = navController)
            }
        }

//        Column(
//            modifier = modifier
//                .fillMaxSize()
//                .background(colorResource(R.color.background))
//        ) {
//            Box(
//                modifier = modifier
//                    .fillMaxSize()
//                    .padding(innerPadding)
//            ) {
//                navItems[selectedItem].screen(mainNavController)
//            }
//
//        }
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

