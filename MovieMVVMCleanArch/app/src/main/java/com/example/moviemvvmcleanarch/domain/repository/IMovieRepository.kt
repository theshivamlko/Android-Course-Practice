package com.example.moviemvvmcleanarch.domain.repository

import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.MovieList
import retrofit2.Response

interface IMovieRepository {
    suspend fun getPopularMovies(): List<Movie>
    suspend fun refreshPopularMovies( ):List<Movie>
//    suspend fun deletePopularMovies( )
}