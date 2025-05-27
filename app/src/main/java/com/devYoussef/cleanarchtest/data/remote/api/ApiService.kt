package com.devYoussef.cleanarchtest.data.remote.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("discover/movie")
    suspend fun getHomeMovies(): Response<>

}