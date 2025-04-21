package com.devYoussef.cleanarchtest.presentation.ui.onboarding

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.devYoussef.cleanarchtest.R
import com.devYoussef.cleanarchtest.navigation.Screens
import com.devYoussef.cleanarchtest.presentation.theme.backgroundDark
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.compareTo
import kotlin.math.abs
import kotlin.math.absoluteValue


@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier, mainNavController: NavController) {

    val pagerState = rememberPagerState { 3 }
    val coroutineScope = rememberCoroutineScope()

    val items = listOf<OnBoardingData>(
        OnBoardingData(
            image = R.drawable.img_boarding_1,
            desc = "watch your favorite movie or serise on\n" + "only one platform. you can waych it\n" + "anytime and anywhere"
        ), OnBoardingData(
            image = R.drawable.img_boarding_2,
            desc = "watch your favorite movie or serise on\n" + "only one platform. you can waych it\n" + "anytime and anywhere"
        ), OnBoardingData(
            image = R.drawable.img_boarding_3,
            desc = "watch your favorite movie or serise on\n" + "only one platform. you can waych it\n" + "anytime and anywhere"
        )
    )

    OnBoardingPager(
        items = items, state = pagerState, modifier = modifier.fillMaxSize(),
        coroutineScope = coroutineScope,
        onNextClick = {
            coroutineScope.launch {
                if (pagerState.currentPage < items.size - 1) {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                } else {
                    mainNavController.navigate(Screens.GetStartedScreen)
                }
            }
        }
    )

}

@Composable
fun OnBoardingPager(
    items: List<OnBoardingData>,
    state: PagerState,
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope,
    onNextClick: () -> Unit = {}

) {
    Scaffold { innerPadding ->

        HorizontalPager(
            state = state,
            userScrollEnabled = false
        ) { page ->

            val currentPageOffset = (
                    (state.currentPage - page) + state.currentPageOffsetFraction
                    ).absoluteValue

            // Fade only current/nearby page, 0 = fully visible, 1+ = invisible
            val alpha = 1f - currentPageOffset.coerceIn(0f, 1f)

            Box(
                modifier = modifier
            ) {

                Image(
                    painter = painterResource(items[page].image),
                    modifier = modifier
                        .fillMaxSize()
//                    .graphicsLayer(alpha = alpha)
                    ,
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                OverLay(modifier = modifier)

            }
        }

        Box(modifier = modifier) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(innerPadding)
                    .padding(bottom = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painter = painterResource(R.drawable.img_logo_text),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color(0xffFF8800)),
                    modifier = Modifier.size(width = 160.dp, height = 40.dp)
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = items[state.currentPage].desc,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(resId = R.font.poppins_regular))
                )

                Spacer(modifier = Modifier.height(20.dp))

                PageIndicator(currentPage = state.currentPage, items = items)

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(
                        onClick = {
                            coroutineScope.launch { // Launch a coroutine
                                if (state.currentPage > 0) {
                                    state.animateScrollToPage(state.currentPage - 1)
                                }
                            }
                        },
                        enabled = state.currentPage > 0,
                    ) {
                        Icon(
                            Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = if (state.currentPage > 0) Color.White else Color(0xFF6B6B6B)
                        )

                        Text(
                            text = "Previous",
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(start = 4.dp),
                            color = if (state.currentPage > 0) Color.White else Color(0xFF6B6B6B)
                        )

                    }

                    TextButton(
                        onClick = {
                            onNextClick()
                        },
                    ) {
                        Text(
                            text = "Next",
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(start = 4.dp),
                            color = Color.White
                        )

                        Icon(
                            Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color.White
                        )

                    }
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
                        Color.Transparent,
                        Color.Transparent,
                        Color(0xFF151515),
                    )
                )
            )
    )
}

@Composable
fun PageIndicator(currentPage: Int, items: List<OnBoardingData>) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(top = 20.dp)
    ) {
        repeat(items.size) {
            Indicator(isSelected = it == currentPage)
        }
    }

}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 50.dp else 25.dp, animationSpec = spring(
            stiffness = Spring.StiffnessMedium, dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )

    Box(
        modifier = Modifier
            .padding(4.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) Color(0xffFF8800) else Color(0xFF6E6E6E)
            )
    )
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun Preview(modifier: Modifier = Modifier) {
    OnBoardingScreen(modifier = modifier, mainNavController = rememberNavController())
}
