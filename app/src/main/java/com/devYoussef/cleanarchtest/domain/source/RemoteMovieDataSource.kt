package com.devYoussef.cleanarchtest.domain.source

import com.devYoussef.cleanarchtest.data.dto.MovieResponseDto
import retrofit2.Response

interface RemoteMovieDataSource {
    suspend fun getHomeMovies(): Response<MovieResponseDto>
}