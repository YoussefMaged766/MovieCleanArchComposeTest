package com.devYoussef.cleanarchtest.data.remote.api

import com.devYoussef.cleanarchtest.data.dto.GenresResponseDto
import com.devYoussef.cleanarchtest.data.dto.MovieResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("discover/movie")
    suspend fun getHomeMovies(): Response<MovieResponseDto>

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): Response<GenresResponseDto>

}