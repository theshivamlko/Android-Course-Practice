package com.example.moviemvvmcleanarch.data.repository

import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.MovieList
import retrofit2.Response

interface IMovieCacheDataSource {

    suspend fun getPopularMoviesFromCache(): List<Movie>
    suspend fun savePopularMoviesToCache(list:List<Movie>)

}