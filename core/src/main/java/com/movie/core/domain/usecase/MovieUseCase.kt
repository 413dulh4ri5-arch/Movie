package com.movie.core.domain.usecase

import com.movie.core.data.Resource
import com.movie.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun searchMovie(query: String): Flow<List<Movie>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(tourism: Movie, state: Boolean)

    fun getSetting(key: String): Boolean

    fun setSetting(key: String, value: Boolean)
}
