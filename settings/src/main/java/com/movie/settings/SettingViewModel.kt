package com.movie.settings

import androidx.lifecycle.ViewModel
import com.movie.core.data.source.local.preference.MovieSharedPreferences
import com.movie.core.domain.usecase.MovieUseCase

class SettingViewModel (private val movieUseCase: MovieUseCase) : ViewModel() {

    fun movieSetting(): Boolean{
        return movieUseCase.getSetting(MovieSharedPreferences.SHARED_PREF_LIST_MOVIE_KEY)
    }

    fun favoriteSetting(): Boolean{
        return movieUseCase.getSetting(MovieSharedPreferences.SHARED_PREF_LIST_FAVORITE_KEY)
    }

    fun changeMovieSetting(isChecked: Boolean){
        movieUseCase.setSetting(MovieSharedPreferences.SHARED_PREF_LIST_MOVIE_KEY, isChecked)
    }

    fun changeFavoriteSetting(isChecked: Boolean){
        movieUseCase.setSetting(MovieSharedPreferences.SHARED_PREF_LIST_FAVORITE_KEY, isChecked)
    }
}
