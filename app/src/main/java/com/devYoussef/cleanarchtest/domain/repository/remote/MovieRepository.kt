package com.devYoussef.cleanarchtest.domain.repository.remote

import com.devYoussef.cleanarchtest.common.model.state.Status
import com.devYoussef.cleanarchtest.data.dto.MovieResponseDto
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getHomeMovies(): Flow<Status<MovieResponseDto>>
}