package com.devYoussef.cleanarchtest.di

import com.devYoussef.cleanarchtest.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    fun provideMovieApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}