package com.movie.capstone.detail

import androidx.lifecycle.ViewModel
import com.movie.core.domain.model.Movie
import com.movie.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun setFavoriteMovie(movie: Movie, newStatus:Boolean){
        return movieUseCase.setFavoriteMovie(movie, newStatus)
    }

}
