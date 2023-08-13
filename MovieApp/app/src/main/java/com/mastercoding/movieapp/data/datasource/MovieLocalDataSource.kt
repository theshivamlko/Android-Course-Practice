package com.mastercoding.movieapp.data.datasource

import com.mastercoding.movieapp.data.model.Movie

interface MovieLocalDataSource {

    suspend fun getMoviesFromDB(): List<Movie>

    suspend fun saveMoviesToDB(movies:List<Movie>)

    suspend fun clearAll()


}