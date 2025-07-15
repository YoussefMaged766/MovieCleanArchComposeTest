package com.devYoussef.cleanarchtest.data.dto

data class GenresResponseDto(
    val genres: List<Genre>?= emptyList()
) {
    data class Genre(
        val id: Int?,
        val name: String?
    )
}