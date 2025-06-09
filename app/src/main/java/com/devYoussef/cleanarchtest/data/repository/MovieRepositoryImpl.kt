package com.devYoussef.cleanarchtest.data.repository

import com.devYoussef.cleanarchtest.common.interfaces.Mapper
import com.devYoussef.cleanarchtest.common.model.state.Status
import com.devYoussef.cleanarchtest.common.network.safeApiCall
import com.devYoussef.cleanarchtest.data.dto.MovieResponseDto
import com.devYoussef.cleanarchtest.data.remote.source.RemoteMovieDataSource
import com.devYoussef.cleanarchtest.domain.model.MovieResponse
import com.devYoussef.cleanarchtest.domain.repository.remote.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteMovieDataSource, // type interface not impl
    private val movieResponseMapper: Mapper<MovieResponseDto, MovieResponse>
) :
    MovieRepository {
    override suspend fun getHomeMovies(): Flow<Status<MovieResponse>> {
        return safeApiCall(
            apiCall = { remoteDataSource.getHomeMovies() },
            responseType = MovieResponseDto::class.java
        ).map {
            when (it) {
                is Status.Success -> {
                    Status.Success(movieResponseMapper.from(it.data))
                }

                is Status.Failure -> {
                    Status.Failure(it.exception)
                }

                is Status.Loading -> {
                    Status.Loading()
                }
            }
        }
    }
}


