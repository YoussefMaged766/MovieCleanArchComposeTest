package com.devYoussef.cleanarchtest.data.remote

import com.devYoussef.cleanarchtest.data.remote.api.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private  val apiService: ApiService)  {

    suspend fun getHomeMovies() = apiService.getHomeMovies()
}