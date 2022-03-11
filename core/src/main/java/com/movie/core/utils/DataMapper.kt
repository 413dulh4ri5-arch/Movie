package com.movie.core.utils

import com.movie.core.data.source.local.entity.MovieEntity
import com.movie.core.data.source.remote.response.MovieResponse
import com.movie.core.domain.model.Movie

object DataMapper {
    private const val indexDomain = "https://image.tmdb.org/t/p/w500/"

    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val tourismList: ArrayList<MovieEntity> = ArrayList()
        input.map {
            val tourism = MovieEntity(
                movieId = it.id,
                title = it.title,
                posterPath = indexDomain + it.posterPath,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                releaseDate = it.releaseDate,
                isFavorite = false
            )
            tourismList.add(tourism)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                posterPath = it.posterPath,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                releaseDate = it.releaseDate,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        posterPath = input.posterPath,
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        releaseDate = input.releaseDate,
        isFavorite = input.isFavorite
    )
}
