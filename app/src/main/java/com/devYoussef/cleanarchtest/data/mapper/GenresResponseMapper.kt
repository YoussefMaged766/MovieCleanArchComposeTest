package com.devYoussef.cleanarchtest.data.mapper

import com.devYoussef.cleanarchtest.common.interfaces.Mapper
import com.devYoussef.cleanarchtest.data.dto.GenresResponseDto
import com.devYoussef.cleanarchtest.domain.model.GenresResponse
import javax.inject.Inject

class GenresResponseMapper @Inject constructor() : Mapper<GenresResponseDto, GenresResponse> {
    override fun from(i: GenresResponseDto?): GenresResponse {
        return GenresResponse(
            genres = i?.genres?.map { genreDto ->
                GenresResponse.Genre(
                    id = genreDto.id,
                    name = genreDto.name
                )
            } ?: emptyList()
        )
    }

    override fun to(o: GenresResponse?): GenresResponseDto {
        return GenresResponseDto(
            genres = o?.genres?.map { genre ->
                GenresResponseDto.Genre(
                    id = genre.id,
                    name = genre.name
                )
            } ?: emptyList()
        )
    }

}