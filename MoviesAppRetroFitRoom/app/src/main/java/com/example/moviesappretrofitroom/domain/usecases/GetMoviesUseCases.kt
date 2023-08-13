package com.example.moviesappretrofitroom.domain.usecases

import com.example.moviesappretrofitroom.data.models.MovieModel
import com.example.moviesappretrofitroom.domain.repositories.MovieRepositories

class GetMoviesUseCases(val movieRepositories: MovieRepositories) {
    suspend fun   execute():List<MovieModel> =movieRepositories.getAllMovies()
}