package com.devYoussef.cleanarchtest.domain.model

data class MovieResponse(
    val page: Int = 1,
    val results: List<Movie> = emptyList(),
    val totalPages: Int = 0,
)
