package com.example.moviemvvmcleanarch.data.repository.movie.datasource

import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.MovieList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IMovieLocalDBDataSource {

    suspend fun getAllMoviesFromDB(): List<Movie>

    suspend fun insertAllMoviesInDB(movies:List<Movie>)

    suspend fun deleteAllMoviesFromDB()

}