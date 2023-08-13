package com.mastercoding.movieapp.data.datasource

import com.mastercoding.movieapp.data.model.Movie

interface MovieCacheDataSource {

    suspend fun getMoviesFromCache(): List<Movie>

    suspend fun saveMoviesToCache(movies:List<Movie>)
}