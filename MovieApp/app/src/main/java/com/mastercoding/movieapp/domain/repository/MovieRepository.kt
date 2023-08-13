package com.mastercoding.movieapp.domain.repository

import com.mastercoding.movieapp.data.model.Movie

interface MovieRepository {

    suspend fun getMovies(): List<Movie>?
    suspend fun updateMovies(): List<Movie>?
}