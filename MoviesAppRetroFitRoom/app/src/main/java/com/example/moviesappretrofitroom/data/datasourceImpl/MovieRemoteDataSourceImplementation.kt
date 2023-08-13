package com.example.moviesappretrofitroom.data.datasourceImpl

import com.example.moviesappretrofitroom.data.api.TMDBService
import com.example.moviesappretrofitroom.data.datasource.MovieRemoteDataSource
import com.example.moviesappretrofitroom.data.models.MoviesListModel
import retrofit2.Response

class MovieRemoteDataSourceImplementation(private val tmdbService:TMDBService,
    private val apiKey:String):MovieRemoteDataSource{
    override suspend fun getAPIMovies(): Response<MoviesListModel> {
        println("getAPIMovies   ${apiKey}")
     return tmdbService.getPopularMovies(apiKey)
    }

}