package com.devYoussef.cleanarchtest.data.mapper

import com.devYoussef.cleanarchtest.common.interfaces.Mapper
import com.devYoussef.cleanarchtest.data.dto.MovieResponseDto
import com.devYoussef.cleanarchtest.domain.model.MovieResponse
import javax.inject.Inject

class MovieResponseMapper @Inject constructor(
    private val movieMapper: MovieMapper
) : Mapper<MovieResponseDto, MovieResponse> {
    override fun from(i: MovieResponseDto?): MovieResponse {
        return MovieResponse(
            page = i?.page ?: 1,
            results = i?.results?.map { movieMapper.from(it) } ?: emptyList(),
            totalPages = i?.total_pages ?: 0,
        )
    }

    override fun to(o: MovieResponse?): MovieResponseDto {
        return MovieResponseDto()
    }
}