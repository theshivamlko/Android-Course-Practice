package com.example.moviemvvmcleanarch.data.repository

import android.database.Observable
import com.example.moviemvvmcleanarch.data.api.TMDBService
import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.MovieList
import com.example.moviemvvmcleanarch.data.roomdb.MovieDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MovieLocalDBDataSourceImpl(private val movieDAO: MovieDAO) : IMovieLocalDBDataSource {
    override suspend fun getAllMoviesFromDB(): Flow<List<Movie>> {
        return flow {
            movieDAO.getAllMovies()
        }
    }

    override suspend fun insertAllMoviesInDB(movies: List<Movie>) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDAO.insertAllMovies(movies)
        }
    }

    override suspend fun deleteAllMoviesFromDB() {
        CoroutineScope(Dispatchers.IO).launch {
            movieDAO.deleteAllMovies()
        }
    }


}