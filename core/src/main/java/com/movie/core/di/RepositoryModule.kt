package com.movie.core.di

import com.movie.core.data.MovieRepository
import com.movie.core.data.source.local.preference.MovieSharedPreferences
import com.movie.core.data.source.local.preference.MovieSharedPreferencesImpl
import com.movie.core.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideIMovieRepository(movieRepository: MovieRepository): IMovieRepository

    @Binds
    abstract fun provideMovieSharedPreferences(movieSharedPreferencesImpl: MovieSharedPreferencesImpl): MovieSharedPreferences

}
