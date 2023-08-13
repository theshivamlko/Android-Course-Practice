package com.example.moviesappretrofitroom.domain.repositories

import com.example.moviesappretrofitroom.data.models.MovieModel

interface MovieRepositories {

    suspend fun getAllMovies():List<MovieModel>
    suspend fun updateAllMovies():List<MovieModel>

}