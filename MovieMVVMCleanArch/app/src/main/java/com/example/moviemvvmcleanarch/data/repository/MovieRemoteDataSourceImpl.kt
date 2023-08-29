package com.example.moviemvvmcleanarch.data.repository

import android.database.Observable
import com.example.moviemvvmcleanarch.data.api.TMDBService
import com.example.moviemvvmcleanarch.data.model.MovieList
import retrofit2.Response

class MovieRemoteDataSourceImpl(val tmdbService: TMDBService) : IMovieRemoteDataSource {
    override suspend fun getPopularMoviesFromRemoteSource(): Response<MovieList> {
        return  tmdbService.getPopularMovies()
    }


}