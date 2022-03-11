package com.movie.settings

import com.movie.core.data.source.local.preference.MovieSharedPreferences.Companion.SHARED_PREF_LIST_FAVORITE_KEY
import com.movie.core.data.source.local.preference.MovieSharedPreferences.Companion.SHARED_PREF_LIST_MOVIE_KEY
import com.movie.core.domain.usecase.MovieUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SettingViewModelUnitTest {

    private lateinit var settingViewModel: SettingViewModel

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @Before
    fun setUp() {
        settingViewModel = SettingViewModel(movieUseCase)
    }

    @Test
    fun `should get movie setting from movieUseCase`() {

        Mockito.`when`(movieUseCase.getSetting(SHARED_PREF_LIST_MOVIE_KEY)).thenReturn(true)

        val result = settingViewModel.movieSetting()

        Mockito.verify(movieUseCase).getSetting(SHARED_PREF_LIST_MOVIE_KEY)
        Assert.assertEquals(true, result)
    }

    @Test
    fun `should get favorite movie setting from movieUseCase`() {

        Mockito.`when`(movieUseCase.getSetting(SHARED_PREF_LIST_FAVORITE_KEY)).thenReturn(false)

        val result = settingViewModel.favoriteSetting()

        Mockito.verify(movieUseCase).getSetting(SHARED_PREF_LIST_FAVORITE_KEY)
        Assert.assertEquals(false, result)
    }

}

