package com.example.moviesappretrofitroom.data.datasource

import com.example.moviesappretrofitroom.data.models.MovieModel

interface MovieCacheDataSource {

    suspend fun getMoviesFromCache():List<MovieModel>
    suspend fun saveMoviesToCache(list:List<MovieModel>)
    suspend fun clearAll()

}