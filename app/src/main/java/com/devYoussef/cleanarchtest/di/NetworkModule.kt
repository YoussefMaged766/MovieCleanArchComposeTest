package com.devYoussef.cleanarchtest.di

import android.net.ConnectivityManager
import com.devYoussef.cleanarchtest.common.network.ConnectivityManagerNetworkMonitor
import com.devYoussef.cleanarchtest.common.network.NetworkMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkMonitor(connectivityManager: ConnectivityManager): NetworkMonitor {
        return ConnectivityManagerNetworkMonitor(connectivityManager)
    }
}