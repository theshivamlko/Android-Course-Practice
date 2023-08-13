package com.mastercoding.movieapp.data.datasourceImpl

import com.mastercoding.movieapp.data.api.TMDBService
import com.mastercoding.movieapp.data.datasource.MovieRemoteDataSource
import com.mastercoding.movieapp.data.model.MovieList
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : MovieRemoteDataSource {
    override suspend fun getMovies(): Response<MovieList> =
        tmdbService.getPopularMovies(apiKey)
}