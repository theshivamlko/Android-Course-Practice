package com.example.moviemvvmcleanarch.data.repository

import android.database.Observable
import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.MovieList
import retrofit2.Response

interface IMovieRemoteDataSource {

    suspend fun getPopularMoviesFromRemoteSource(): Response<MovieList>

}