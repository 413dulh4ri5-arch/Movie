package com.movie.core.domain.repository

import com.movie.core.data.Resource
import com.movie.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun searchMovie(query: String): Flow<List<Movie>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

    fun getSetting(key: String): Boolean

    fun setSetting(key: String, value: Boolean)

}
