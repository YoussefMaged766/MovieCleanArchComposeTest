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
    private val movieRepository: MovieRepository,
    private val movieMapper: Mapper<MovieResponseDto, MovieResponse>
) {

    suspend operator fun invoke(): Flow<Status<MovieResponse>> {
        return movieRepository.getHomeMovies().map { status ->
            when (status) {
                is Status.Success -> Status.Success(movieMapper.from(status.data))
                is Status.Failure -> Status.Failure(status.exception)
                is Status.Loading -> Status.Loading(status.loading)
            }
        }
    }
}