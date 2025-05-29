package com.devYoussef.cleanarchtest.di

import com.devYoussef.cleanarchtest.data.remote.RemoteDataSource
import com.devYoussef.cleanarchtest.data.repository.MovieRepositoryImpl
import com.devYoussef.cleanarchtest.domain.repository.remote.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMovieRepository(
        remoteDataSource: RemoteDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(remoteDataSource)
    }
}