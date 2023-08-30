package com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl

import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieCacheDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.ITVShowCacheDataSource



class MovieCacheDataSourceImpl : IMovieCacheDataSource {

    private val movieList = mutableListOf<Movie>()

    override suspend fun getPopularMoviesFromCache(): List<Movie> {
        return  movieList
    }

    override suspend fun savePopularMoviesToCache(list: List<Movie>) {
        movieList.clear()
        movieList.addAll(list)
    }


}