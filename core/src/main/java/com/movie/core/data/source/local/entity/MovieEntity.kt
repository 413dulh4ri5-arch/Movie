package com.movie.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "movie_id") var movieId: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "poster_path") var posterPath: String,
    @ColumnInfo(name = "overview") var overview: String,
    @ColumnInfo(name = "original_language") var originalLanguage: String,
    @ColumnInfo(name = "original_title") var originalTitle: String,
    @ColumnInfo(name = "release_date") var releaseDate: String,
    @ColumnInfo(name = "is_favorite") var isFavorite: Boolean = false
)

