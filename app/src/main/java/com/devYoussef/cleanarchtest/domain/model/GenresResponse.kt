package com.devYoussef.cleanarchtest.domain.model

data class GenresResponse(
    val genres: List<Genre>?= emptyList()
) {
    data class Genre(
        val id: Int?,
        val name: String?
    )
}