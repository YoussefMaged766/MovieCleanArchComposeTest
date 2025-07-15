package com.devYoussef.cleanarchtest.data.repository

import com.devYoussef.cleanarchtest.common.extentions.mapStatus
import com.devYoussef.cleanarchtest.common.interfaces.Mapper
import com.devYoussef.cleanarchtest.common.model.exception.HandleExceptions
import com.devYoussef.cleanarchtest.common.model.state.Status
import com.devYoussef.cleanarchtest.common.network.safeApiCall
import com.devYoussef.cleanarchtest.data.dto.GenresResponseDto
import com.devYoussef.cleanarchtest.data.dto.MovieResponseDto
import com.devYoussef.cleanarchtest.data.remote.source.RemoteMovieDataSource
import com.devYoussef.cleanarchtest.domain.model.GenresResponse
import com.devYoussef.cleanarchtest.domain.model.MovieResponse
import com.devYoussef.cleanarchtest.domain.repository.remote.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteMovieDataSource, // type interface not impl
    private val movieResponseMapper: Mapper<MovieResponseDto, MovieResponse>,
     private val genresResponseMapper: Mapper<GenresResponseDto, GenresResponse>
) : MovieRepository {
    override suspend fun getHomeMovies(): Flow<Status<MovieResponse>> {
        return safeApiCall(
            apiCall = { remoteDataSource.getHomeMovies() },
            responseType = MovieResponseDto::class.java
        ).mapStatus(movieResponseMapper::from)
    }

    override suspend fun getMovieGenres(): Flow<Status<GenresResponse>> {
        return safeApiCall(
            apiCall = { remoteDataSource.getMovieGenres() },
            responseType = GenresResponseDto::class.java
        ).mapStatus(genresResponseMapper::from)
    }
}


