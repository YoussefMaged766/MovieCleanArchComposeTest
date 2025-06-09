package com.devYoussef.cleanarchtest.domain.repository.remote

import com.devYoussef.cleanarchtest.common.model.state.Status
import com.devYoussef.cleanarchtest.data.dto.MovieResponseDto
import com.devYoussef.cleanarchtest.domain.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getHomeMovies(): Flow<Status<MovieResponse>> /// data class for domain not in data layer
}