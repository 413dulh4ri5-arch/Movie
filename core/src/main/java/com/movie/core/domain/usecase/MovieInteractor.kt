package com.movie.core.domain.usecase

import com.movie.core.domain.model.Movie
import com.movie.core.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val iMovieRepository: IMovieRepository)
    : MovieUseCase {

    override fun getAllMovie() = iMovieRepository.getAllMovie()

    override fun searchMovie(query: String) = iMovieRepository.searchMovie(query)

    override fun getFavoriteMovie() = iMovieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(tourism: Movie, state: Boolean) =
        iMovieRepository.setFavoriteMovie(tourism, state)

    override fun getSetting(key: String) = iMovieRepository.getSetting(key)

    override fun setSetting(key: String, value: Boolean) = iMovieRepository.setSetting(key, value)
}
