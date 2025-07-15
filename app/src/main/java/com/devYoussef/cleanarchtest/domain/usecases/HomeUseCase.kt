package com.devYoussef.cleanarchtest.domain.usecases

import android.util.Log
import com.devYoussef.cleanarchtest.common.model.exception.HandleExceptions
import com.devYoussef.cleanarchtest.common.model.state.Status
import com.devYoussef.cleanarchtest.domain.model.GenresResponse
import com.devYoussef.cleanarchtest.domain.model.MovieResponse
import com.devYoussef.cleanarchtest.domain.repository.remote.MovieRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): Flow<Status<MovieResponse>> {
        return movieRepository.getHomeMovies()
    }

    class GenreUseCase @Inject constructor(
        private val movieRepository: MovieRepository
    ) {
        suspend operator fun invoke(): Flow<Status<GenresResponse>> {
            return movieRepository.getMovieGenres()
        }
    }

//    operator fun invoke(): Flow<Status<HomeScreenData>> = flow {
//        emit(Status.Loading())
//
//        try {
//            val movies: Status<MovieResponse>
//            val genres: Status<GenresResponse>
//
//            coroutineScope {
//                val moviesDeferred = async {
//                    movieRepository.getHomeMovies().first()
//                }
//                val genresDeferred = async {
//                    movieRepository.getMovieGenres().first()
//                }
//
//                movies = moviesDeferred.await()
//                genres = genresDeferred.await()
//            }
//
//            when {
//                movies is Status.Success && genres is Status.Success -> {
//                    emit(Status.Success(HomeScreenData(movies = movies.data, genres = genres.data)))
//                }
//                movies is Status.Failure -> {
//                    emit(Status.Failure(movies.exception))
//                }
//                genres is Status.Failure -> {
//                    emit(Status.Failure(genres.exception))
//                }
//                else -> {
//                    emit(Status.Loading()) // fallback, rarely needed
//                }
//            }
//        } catch (e: Exception) {
//            emit(Status.Failure(HandleExceptions.UnknownException(e.message ?: "Unknown error")))
//        }
//    }

}




//data class HomeScreenData(
//    val movies: MovieResponse,
//    val genres: GenresResponse
//)