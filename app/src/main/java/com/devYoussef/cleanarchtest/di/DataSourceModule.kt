package com.devYoussef.cleanarchtest.di

import com.devYoussef.cleanarchtest.data.remote.RemoteDataSourceImpl
import com.devYoussef.cleanarchtest.data.remote.source.RemoteMovieDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract  class  DataSourceModule {

    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImp: RemoteDataSourceImpl): RemoteMovieDataSource
}