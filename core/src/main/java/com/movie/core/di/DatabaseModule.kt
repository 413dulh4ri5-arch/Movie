package com.movie.core.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.movie.core.data.source.local.preference.MovieSharedPreferences
import com.movie.core.data.source.local.room.MovieDao
import com.movie.core.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("movie".toCharArray())
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java, "Movies.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(SupportFactory(passphrase))
            .build()
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()

    @Provides
    @Singleton
    fun provideDefaultSharedPreferences(@ApplicationContext applicationContext: Context): SharedPreferences {
        return applicationContext.getSharedPreferences(
            MovieSharedPreferences.SHARED_PREF_KEY,
            Context.MODE_PRIVATE
        )
    }
}
