package com.devYoussef.cleanarchtest.di

import com.devYoussef.cleanarchtest.common.interfaces.Mapper
import com.devYoussef.cleanarchtest.data.dto.MovieDto
import com.devYoussef.cleanarchtest.data.dto.MovieResponseDto
import com.devYoussef.cleanarchtest.data.mapper.MovieMapper
import com.devYoussef.cleanarchtest.data.mapper.MovieResponseMapper
import com.devYoussef.cleanarchtest.domain.model.Movie
import com.devYoussef.cleanarchtest.domain.model.MovieResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindMovieResponseMapper(mapper: MovieResponseMapper): Mapper<MovieResponseDto, MovieResponse>


    @Binds
    abstract fun bindMovieMapper(mapper: MovieMapper): Mapper<MovieDto, Movie>
}