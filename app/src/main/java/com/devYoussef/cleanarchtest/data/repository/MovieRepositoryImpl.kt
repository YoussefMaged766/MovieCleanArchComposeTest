package com.devYoussef.cleanarchtest.data.repository

import com.devYoussef.cleanarchtest.common.model.state.Status
import com.devYoussef.cleanarchtest.common.network.safeApiCall
import com.devYoussef.cleanarchtest.data.dto.MovieResponseDto
import com.devYoussef.cleanarchtest.data.remote.RemoteDataSourceImpl
import com.devYoussef.cleanarchtest.domain.repository.remote.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSourceImpl
) :
    MovieRepository {
    override suspend fun getHomeMovies(): Flow<Status<MovieResponseDto>> {
        return safeApiCall(
            apiCall = { remoteDataSource.getHomeMovies() },
            responseType = MovieResponseDto::class.java
        )
    }
}
