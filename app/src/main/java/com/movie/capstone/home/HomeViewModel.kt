package com.movie.capstone.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.movie.core.data.Resource
import com.movie.core.domain.model.Movie
import com.movie.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    fun movie(): LiveData<Resource<List<Movie>>> {
        return movieUseCase.getAllMovie().asLiveData()
    }

    fun searchMovie(query: String): LiveData<List<Movie>> {
        return movieUseCase.searchMovie(query).asLiveData()
    }
}
