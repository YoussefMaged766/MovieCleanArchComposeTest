package com.devYoussef.cleanarchtest.presentation.ui.home


import android.util.Log
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.util.DebugLogger
import com.devYoussef.cleanarchtest.R
import com.devYoussef.cleanarchtest.common.model.state.Status
import com.devYoussef.cleanarchtest.domain.model.Movie
import com.devYoussef.cleanarchtest.domain.model.MovieResponse
import com.devYoussef.cleanarchtest.presentation.ui.base.ConnectivityWrapper


@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    mainNavController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
    innerPadding: PaddingValues
) {
    val state by viewModel.state.collectAsState()
    val isOnline by viewModel.isOnline.collectAsState()
    var isRefreshing by remember { mutableStateOf(false) }
    val refreshState = rememberPullToRefreshState()

    LaunchedEffect(isOnline) {
        if (isOnline) viewModel.fetchHomeMovies()
    }

    Scaffold(modifier = modifier) { _ ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.background))
        ) {
            ConnectivityWrapper(
                isOnline = viewModel.isOnline,
                exception = (state as? Status.Failure)?.exception,
                onRetry = { viewModel.fetchHomeMovies() },
                effect = viewModel.uiEffect,
                snackbarHostState = snackbarHostState
            )

            HomeContent(
                state = state,
                isRefreshing = isRefreshing,
                onRefresh = {
                    if (isOnline) viewModel.fetchHomeMovies()
                    isRefreshing = true
                },
                onRefreshCompleted = { isRefreshing = false },
                refreshState = refreshState,
                innerPadding = innerPadding
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    state: Status<MovieResponse>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onRefreshCompleted: () -> Unit,
    refreshState: PullToRefreshState,
    innerPadding: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background))
            .nestedScroll(rememberNestedScrollInteropConnection())
    ) {
        when (state) {
            is Status.Loading -> {
                // Optionally show empty grid for gesture support
                MovieGrid(
                    movies = emptyList(),
                    innerPadding = innerPadding,
                    isRefreshing = isRefreshing,
                    refreshState = refreshState,
                    onRefresh = onRefresh
                )
            }

            is Status.Success -> {
                onRefreshCompleted()
                MovieGrid(
                    movies = state.data.results,
                    innerPadding = innerPadding,
                    isRefreshing = isRefreshing,
                    refreshState = refreshState,
                    onRefresh = onRefresh
                )
            }

            is Status.Failure -> {
                onRefreshCompleted()
                // Optional error or fallback UI
                MovieGrid(
                    movies = emptyList(),
                    innerPadding = innerPadding,
                    isRefreshing = isRefreshing,
                    refreshState = refreshState,
                    onRefresh = onRefresh
                )
            }
        }

        // ✅ This now has access to BoxScope — align works
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(innerPadding)
        ) {
            PullToRefreshIndicator(
                isRefreshing = isRefreshing,
                refreshState = refreshState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieGrid(
    movies: List<Movie>,
    innerPadding: PaddingValues,
    isRefreshing: Boolean,
    refreshState: PullToRefreshState,
    onRefresh: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .pullToRefresh(
                state = refreshState,
                isRefreshing = isRefreshing,
                onRefresh = onRefresh
            ),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(movies.size) { index ->
            MovieItem(
                modifier = Modifier.padding(2.dp),
                movie = movies[index]
            )
        }
    }
}

@Composable
fun MovieItem(modifier: Modifier = Modifier, movie: Movie) {
    Column(
        modifier = modifier
            .size(width = 116.dp, height = 154.dp)
            .clip(RoundedCornerShape(18.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(116.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(Color.Black.copy(alpha = 0.2f))
        ) {
            AsyncImage(
                model = movie.imagePath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize(),
                placeholder = painterResource(R.drawable.ic_home),
                error = painterResource(R.drawable.ic_setting)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.title,
            color = Color.White,
            fontSize = 14.sp,
            maxLines = 1,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun PullToRefreshIndicator(
    isRefreshing: Boolean,
    refreshState: PullToRefreshState
) {
    PullToRefreshDefaults.LoadingIndicator(
        state = refreshState,
        isRefreshing = isRefreshing
    )
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

