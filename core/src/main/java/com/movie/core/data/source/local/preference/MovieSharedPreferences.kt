package com.movie.core.data.source.local.preference

interface MovieSharedPreferences {

    fun getInt(keyName: String, defaultValue: Int): Int

    fun putInt(keyName: String, value: Int)

    fun getBoolean(keyName: String, defaultValue: Boolean): Boolean

    fun putBoolean(keyName: String, value: Boolean)

    fun getString(keyName: String, defaultValue: String): String

    fun putString(keyName: String, value: String)

    companion object {
        const val SHARED_PREF_KEY = "com.movie.core.data.source.local.preference.MovieSharedPreferences"
        const val SHARED_PREF_LIST_MOVIE_KEY = "SHARED_PREF_LIST_MOVIE_KEY"
        const val SHARED_PREF_LIST_FAVORITE_KEY = "SHARED_PREF_LIST_FAVORITE_KEY"
    }
}
