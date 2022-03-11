package com.movie.core.data.source.local

import com.movie.core.data.source.local.entity.MovieEntity
import com.movie.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getAllMovie(isSetting: Boolean): Flow<List<MovieEntity>> = movieDao.getAllMovie(isSetting)

    fun searchMovie(isSetting: Boolean, query: String): Flow<List<MovieEntity>> =
        movieDao.searchMovie(isSetting, query)

    fun getFavoriteMovie(isSetting: Boolean): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie(isSetting)

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}
