package com.movie.core.data.source.remote.network

import com.movie.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("top_rated?api_key=a565c718892f735b411ce62206489ebc")
    suspend fun getList(): ListMovieResponse
}
