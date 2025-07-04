package com.devYoussef.cleanarchtest.data.mapper

import com.devYoussef.cleanarchtest.common.constants.Constants
import com.devYoussef.cleanarchtest.common.interfaces.Mapper
import com.devYoussef.cleanarchtest.data.dto.MovieDto
import com.devYoussef.cleanarchtest.domain.model.Movie
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieDto, Movie> {
    override fun from(i: MovieDto?): Movie {
        return Movie(
            id = i?.id ?: 0,
            title = i?.title ?: "",
            imagePath = "${Constants.BASE_IMAGES_URL}${i?.backdrop_path ?: ""}"
        )
    }

    override fun to(o: Movie?): MovieDto {
        return MovieDto()
    }
}