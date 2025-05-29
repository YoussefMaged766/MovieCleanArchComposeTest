package com.devYoussef.cleanarchtest.data.dto

data class MovieResponseDto(
    val page: Int?=null,
    val results: List<MovieDto>?=null,
    val total_pages: Int?=null,
    val total_results: Int?=null
)