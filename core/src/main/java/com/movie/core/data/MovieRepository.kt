package com.movie.core.data

import com.movie.core.data.source.local.LocalDataSource
import com.movie.core.data.source.local.preference.MovieSharedPreferences
import com.movie.core.data.source.local.preference.MovieSharedPreferences.Companion.SHARED_PREF_LIST_FAVORITE_KEY
import com.movie.core.data.source.local.preference.MovieSharedPreferences.Companion.SHARED_PREF_LIST_MOVIE_KEY
import com.movie.core.data.source.remote.RemoteDataSource
import com.movie.core.data.source.remote.network.ApiResponse
import com.movie.core.data.source.remote.response.MovieResponse
import com.movie.core.domain.model.Movie
import com.movie.core.domain.repository.IMovieRepository
import com.movie.core.utils.AppExecutors
import com.movie.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
    private val movieSharedPreferences: MovieSharedPreferences
) : IMovieRepository {

    override fun searchMovie(query: String): Flow<List<Movie>> {
        return localDataSource.searchMovie(getSetting(SHARED_PREF_LIST_MOVIE_KEY), query).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie(getSetting(SHARED_PREF_LIST_MOVIE_KEY)).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllTourism()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie(getSetting(SHARED_PREF_LIST_FAVORITE_KEY)).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

    override fun getSetting(key: String): Boolean {
        return movieSharedPreferences.getBoolean(key, false)
    }

    override fun setSetting(key: String, value: Boolean) {
        movieSharedPreferences.putBoolean(key, value)
    }
}


