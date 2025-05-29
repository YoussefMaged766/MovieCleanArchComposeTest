package com.devYoussef.cleanarchtest.domain.usecases

import com.devYoussef.cleanarchtest.domain.repository.remote.MovieRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke() = movieRepository.getHomeMovies()
}