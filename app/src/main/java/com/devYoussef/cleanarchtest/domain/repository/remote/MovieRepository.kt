package com.devYoussef.cleanarchtest.domain.repository.remote

import com.devYoussef.cleanarchtest.common.model.state.Status
import com.devYoussef.cleanarchtest.domain.model.GenresResponse
import com.devYoussef.cleanarchtest.domain.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getHomeMovies(): Flow<Status<MovieResponse>>

    suspend fun getMovieGenres(): Flow<Status<GenresResponse>>
}