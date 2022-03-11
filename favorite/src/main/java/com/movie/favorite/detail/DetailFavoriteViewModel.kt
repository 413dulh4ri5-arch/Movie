package com.movie.favorite.detail

import androidx.lifecycle.ViewModel
import com.movie.core.domain.model.Movie
import com.movie.core.domain.usecase.MovieUseCase

class DetailFavoriteViewModel (private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus:Boolean) {
        movieUseCase.setFavoriteMovie(movie, newStatus)
    }
}
