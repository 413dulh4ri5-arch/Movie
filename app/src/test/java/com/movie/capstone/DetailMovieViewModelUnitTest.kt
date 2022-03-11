package com.movie.capstone

import com.movie.capstone.detail.DetailMovieViewModel
import com.movie.core.domain.model.Movie
import com.movie.core.domain.usecase.MovieUseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelUnitTest {

    private lateinit var detailMovieViewModel: DetailMovieViewModel

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @Before
    fun setUp() {
        detailMovieViewModel = DetailMovieViewModel(movieUseCase)
    }

    @Test
    fun `should set favorite movie from movieUseCase`() {

        val fakeMovie = Movie(
            movieId = 12,
            title = "title",
            posterPath = "posterPath",
            overview = "overview",
            originalLanguage = "en",
            originalTitle = "originalTitle",
            releaseDate = "1992-27-02",
            isFavorite = false
        )

        detailMovieViewModel.setFavoriteMovie(fakeMovie, true)
        Mockito.verify(movieUseCase).setFavoriteMovie(fakeMovie, true)
    }

}


