package com.movie.capstone.domain

import com.movie.core.data.Resource
import com.movie.core.data.source.local.preference.MovieSharedPreferences.Companion.SHARED_PREF_LIST_MOVIE_KEY
import com.movie.core.domain.model.Movie
import com.movie.core.domain.repository.IMovieRepository
import com.movie.core.domain.usecase.MovieInteractor
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
class MovieUseCaseTest {

    private lateinit var movieUseCase: MovieUseCase

    @Mock
    private lateinit var movieRepository: IMovieRepository

    @Before
    fun setUp() {
        movieUseCase = MovieInteractor(movieRepository)
    }

    @Test
    fun `should get all movie from repository`() {

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

        Mockito.`when`(movieRepository.getAllMovie())
            .thenReturn(flowOf(Resource.Success(listOf(fakeMovie))))

        movieUseCase.getAllMovie()

        Mockito.verify(movieRepository).getAllMovie()
        Assert.assertEquals(1, listOf(fakeMovie).size)
    }

    @Test
    fun `should search movie from repository`() {

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

        Mockito.`when`(movieRepository.searchMovie(fakeQuery))
            .thenReturn(flowOf(listOf(fakeMovie)))

        movieUseCase.searchMovie(fakeQuery)

        Mockito.verify(movieRepository).searchMovie(fakeQuery)
        Assert.assertEquals(1, listOf(fakeMovie).size)
    }

    @Test
    fun `should get favorite movie from repository`() {

        val fakeMovie = Movie(
            movieId = 12,
            title = "title",
            posterPath = "posterPath",
            overview = "overview",
            originalLanguage = "en",
            originalTitle = "originalTitle",
            releaseDate = "1992-27-02",
            isFavorite = true
        )

        Mockito.`when`(movieRepository.getFavoriteMovie())
            .thenReturn(flowOf(listOf(fakeMovie)))

        movieUseCase.getFavoriteMovie()

        Mockito.verify(movieRepository).getFavoriteMovie()
        Assert.assertEquals(1, listOf(fakeMovie).size)
    }

    @Test
    fun `should set favorite movie from repository`() {

        val fakeMovie = Movie(
            movieId = 12,
            title = "title",
            posterPath = "posterPath",
            overview = "overview",
            originalLanguage = "en",
            originalTitle = "originalTitle",
            releaseDate = "1992-27-02",
            isFavorite = true
        )

        movieUseCase.setFavoriteMovie(fakeMovie, true)
        Mockito.verify(movieRepository).setFavoriteMovie(fakeMovie, true)
    }

    @Test
    fun `should unset favorite movie from repository`() {

        val fakeMovie = Movie(
            movieId = 12,
            title = "title",
            posterPath = "posterPath",
            overview = "overview",
            originalLanguage = "en",
            originalTitle = "originalTitle",
            releaseDate = "1992-27-02",
            isFavorite = true
        )

        movieUseCase.setFavoriteMovie(fakeMovie, false)
        Mockito.verify(movieRepository).setFavoriteMovie(fakeMovie, false)
    }

    @Test
    fun `should get setting from repository`() {

        Mockito.`when`(movieRepository.getSetting(SHARED_PREF_LIST_MOVIE_KEY))
            .thenReturn(true)

        val result = movieUseCase.getSetting(SHARED_PREF_LIST_MOVIE_KEY)

        Mockito.verify(movieRepository).getSetting(SHARED_PREF_LIST_MOVIE_KEY)
        Assert.assertEquals(true, result)
    }

    @Test
    fun `should set setting from repository`() {

        movieUseCase.getSetting(SHARED_PREF_LIST_MOVIE_KEY)

        Mockito.verify(movieRepository).getSetting(SHARED_PREF_LIST_MOVIE_KEY)
    }

}
