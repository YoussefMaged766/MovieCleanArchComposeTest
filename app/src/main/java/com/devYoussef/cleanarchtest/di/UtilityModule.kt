package com.devYoussef.cleanarchtest.di

import android.content.Context
import android.net.ConnectivityManager
import com.devYoussef.cleanarchtest.common.network.ConnectivityManagerNetworkMonitor
import com.devYoussef.cleanarchtest.common.network.NetworkMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilityModule {

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideNetworkMonitor(connectivityManager: ConnectivityManager): NetworkMonitor {
        return ConnectivityManagerNetworkMonitor(connectivityManager)
    }

}