package com.movie.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: Int,
    val title: String,
    val posterPath: String,
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val releaseDate: String,
    val isFavorite: Boolean
) : Parcelable
