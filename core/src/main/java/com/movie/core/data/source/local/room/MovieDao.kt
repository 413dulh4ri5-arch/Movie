package com.movie.core.data.source.local.room

import androidx.room.*
import com.movie.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(tourism: List<MovieEntity>)

    @Query("SELECT * FROM movie ORDER BY " +
            "  CASE WHEN :isSetting = 1 THEN title END ASC, " +
            "  CASE WHEN :isSetting = 0 THEN title END DESC")
    fun getAllMovie(isSetting: Boolean): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE title LIKE '%'||:query||'%' ORDER BY " +
            "  CASE WHEN :isSetting = 1 THEN title END ASC, " +
            "  CASE WHEN :isSetting = 0 THEN title END DESC ")
    fun searchMovie(isSetting: Boolean, query: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where is_favorite = 1 ORDER BY " +
            "  CASE WHEN :isSetting = 1 THEN title END ASC, " +
            "  CASE WHEN :isSetting = 0 THEN title END DESC")
    fun getFavoriteMovie(isSetting: Boolean): Flow<List<MovieEntity>>

    @Update
    fun updateFavoriteMovie(movieEntity: MovieEntity)
}

