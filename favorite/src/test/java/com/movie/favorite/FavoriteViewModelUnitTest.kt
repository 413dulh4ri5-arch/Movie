package com.movie.favorite

import com.movie.core.domain.model.Movie
import com.movie.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelUnitTest {

    private lateinit var favoriteViewModel: FavoriteViewModel

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @Before
    fun setUp() {
        favoriteViewModel = FavoriteViewModel(movieUseCase)
    }

    @Test
    fun `should get favorite movie from movieUseCase`() {

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

        Mockito.`when`(movieUseCase.getFavoriteMovie())
            .thenReturn(flowOf(listOf(fakeMovie)))

        favoriteViewModel.favorite()

        Mockito.verify(movieUseCase).getFavoriteMovie()
    }

}
