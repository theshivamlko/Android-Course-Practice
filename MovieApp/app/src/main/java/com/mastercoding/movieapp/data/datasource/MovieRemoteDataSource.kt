package com.mastercoding.movieapp.data.datasource

import com.mastercoding.movieapp.data.model.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {

    suspend fun getMovies() : Response<MovieList>
}