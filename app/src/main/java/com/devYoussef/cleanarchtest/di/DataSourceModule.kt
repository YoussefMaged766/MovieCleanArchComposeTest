package com.devYoussef.cleanarchtest.di

import com.devYoussef.cleanarchtest.data.remote.RemoteDataSource
import com.devYoussef.cleanarchtest.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideRemoteDataSource(
        apiService: ApiService
    ): RemoteDataSource {
        return RemoteDataSource(apiService)
    }
}