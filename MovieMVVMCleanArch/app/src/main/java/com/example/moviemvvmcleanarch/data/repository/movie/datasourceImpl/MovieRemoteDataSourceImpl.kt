package com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl

import com.example.moviemvvmcleanarch.data.api.TMDBService
import com.example.moviemvvmcleanarch.data.model.MovieList
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieRemoteDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.ITVShowRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(val tmdbService: TMDBService) : IMovieRemoteDataSource {
    override suspend fun getPopularMoviesFromRemoteSource(): Response<MovieList> {
        return  tmdbService.getPopularMovies()
    }


}