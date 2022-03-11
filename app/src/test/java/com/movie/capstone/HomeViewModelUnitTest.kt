package com.movie.capstone

import com.movie.capstone.home.HomeViewModel
import com.movie.core.data.Resource
import com.movie.core.domain.model.Movie
import com.movie.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelUnitTest {

    private lateinit var homeViewModel: HomeViewModel

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(movieUseCase)
    }

    @Test
    fun `should get all movie from movieUseCase`() {

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

        Mockito.`when`(movieUseCase.getAllMovie())
            .thenReturn(flowOf(Resource.Success(listOf(fakeMovie))))

        homeViewModel.movie()

        Mockito.verify(movieUseCase).getAllMovie()
    }

    @Test
    fun `should search movie from movieUseCase`() {

        val fakeQuery = "query"
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

        Mockito.`when`(movieUseCase.searchMovie(fakeQuery))
            .thenReturn(flowOf(listOf(fakeMovie)))

        homeViewModel.searchMovie(fakeQuery)

        Mockito.verify(movieUseCase).searchMovie(fakeQuery)
        Assert.assertEquals(1, listOf(fakeMovie).size)
    }

}

