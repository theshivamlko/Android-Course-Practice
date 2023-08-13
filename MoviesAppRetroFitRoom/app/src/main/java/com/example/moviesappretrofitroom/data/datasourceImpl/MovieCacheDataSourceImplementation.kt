package com.example.moviesappretrofitroom.data.datasourceImpl

import com.example.moviesappretrofitroom.data.datasource.MovieCacheDataSource
import com.example.moviesappretrofitroom.data.models.MovieModel

class MovieCacheDataSourceImplementation:MovieCacheDataSource {

    private var moviesList=ArrayList<MovieModel>()

    override suspend fun getMoviesFromCache(): List<MovieModel> {
        return  moviesList
    }

    override suspend fun saveMoviesToCache(list: List<MovieModel>) {
        moviesList.clear()
        moviesList=ArrayList(moviesList)
    }


    override suspend fun clearAll() {
        moviesList.clear()
    }
}