package com.devYoussef.cleanarchtest.data.remote.source

import com.devYoussef.cleanarchtest.data.dto.GenresResponseDto
import com.devYoussef.cleanarchtest.data.dto.MovieResponseDto
import retrofit2.Response

interface RemoteMovieDataSource {
    suspend fun getHomeMovies(): Response<MovieResponseDto>

    suspend fun  getMovieGenres(): Response<GenresResponseDto>
}