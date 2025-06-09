package com.devYoussef.cleanarchtest.domain.usecases

import com.devYoussef.cleanarchtest.common.interfaces.Mapper
import com.devYoussef.cleanarchtest.common.model.state.Status
import com.devYoussef.cleanarchtest.data.dto.MovieResponseDto
import com.devYoussef.cleanarchtest.domain.model.MovieResponse
import com.devYoussef.cleanarchtest.domain.repository.remote.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(): Flow<Status<MovieResponse>> {
        return movieRepository.getHomeMovies()
    }
}