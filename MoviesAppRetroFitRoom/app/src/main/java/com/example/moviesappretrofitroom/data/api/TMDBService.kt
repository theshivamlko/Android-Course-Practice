package com.example.moviesappretrofitroom.data.api

import com.example.moviesappretrofitroom.data.models.MoviesListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MoviesListModel>

}