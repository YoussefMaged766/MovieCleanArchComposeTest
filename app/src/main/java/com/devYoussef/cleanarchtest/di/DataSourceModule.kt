package com.devYoussef.cleanarchtest.di

import com.devYoussef.cleanarchtest.data.remote.RemoteDataSourceImpl
import com.devYoussef.cleanarchtest.data.remote.api.ApiService
import com.devYoussef.cleanarchtest.domain.source.RemoteMovieDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract  class  DataSourceModule {

    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImp: RemoteDataSourceImpl): RemoteMovieDataSource
}