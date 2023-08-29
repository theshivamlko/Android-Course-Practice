package com.example.moviemvvmcleanarch.data.repository

import android.database.Observable
import com.example.moviemvvmcleanarch.data.api.TMDBService
import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.data.model.MovieList
import com.example.moviemvvmcleanarch.data.roomdb.MovieDAO
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Singleton


@Singleton
class MovieCacheDataSourceImpl( ) : IMovieCacheDataSource {

    private val movieList = mutableListOf<Movie>()

    override suspend fun getPopularMoviesFromCache(): List<Movie> {
        return  movieList
    }

    override suspend fun savePopularMoviesToCache(list: List<Movie>) {
        movieList.clear()
        movieList.addAll(list)
    }


}