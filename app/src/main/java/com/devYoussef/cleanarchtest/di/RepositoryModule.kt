package com.devYoussef.cleanarchtest.di

import com.devYoussef.cleanarchtest.data.remote.RemoteDataSourceImpl
import com.devYoussef.cleanarchtest.data.repository.MovieRepositoryImpl
import com.devYoussef.cleanarchtest.domain.repository.remote.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract  class  RepositoryModule {

//    @Provides
//    fun provideMovieRepository(
//        remoteDataSource: RemoteDataSourceImpl
//    ): MovieRepository {
//        return MovieRepositoryImpl(remoteDataSource)
//    }

    @Binds
    @ViewModelScoped
    abstract fun provideMovieRepository(repository: MovieRepositoryImpl): MovieRepository
}