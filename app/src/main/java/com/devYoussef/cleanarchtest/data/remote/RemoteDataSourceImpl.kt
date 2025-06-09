package com.devYoussef.cleanarchtest.data.remote

import com.devYoussef.cleanarchtest.data.dto.MovieResponseDto
import com.devYoussef.cleanarchtest.data.remote.api.ApiService
import com.devYoussef.cleanarchtest.data.remote.source.RemoteMovieDataSource
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    RemoteMovieDataSource {
    override suspend fun getHomeMovies(): Response<MovieResponseDto> {
        return apiService.getHomeMovies()
    }
}