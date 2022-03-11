package com.movie.core.data.source.local.preference

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieSharedPreferencesImpl
@Inject constructor(private val sharedPreferences: SharedPreferences) : MovieSharedPreferences {

    override fun getInt(keyName: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(keyName, defaultValue)
    }

    override fun putInt(keyName: String, value: Int) {
        sharedPreferences.edit().putInt(keyName, value).apply()
    }

    override fun getBoolean(keyName: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(keyName, defaultValue)
    }

    override fun putBoolean(keyName: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(keyName, value).apply()
    }

    override fun getString(keyName: String, defaultValue: String): String {
        return sharedPreferences.getString(keyName, defaultValue) ?: defaultValue
    }

    override fun putString(keyName: String, value: String) {
        sharedPreferences.edit().putString(keyName, value).apply()
    }
}

