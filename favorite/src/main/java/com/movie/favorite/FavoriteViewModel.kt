package com.movie.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.movie.core.domain.model.Movie
import com.movie.core.domain.usecase.MovieUseCase

class FavoriteViewModel (private val movieUseCase: MovieUseCase) : ViewModel() {

    fun favorite(): LiveData<List<Movie>> {
        return movieUseCase.getFavoriteMovie().asLiveData()
    }

}
