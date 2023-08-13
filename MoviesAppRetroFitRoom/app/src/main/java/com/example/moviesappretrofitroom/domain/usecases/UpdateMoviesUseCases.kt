package com.example.moviesappretrofitroom.domain.usecases

import com.example.moviesappretrofitroom.data.models.MovieModel
import com.example.moviesappretrofitroom.domain.repositories.MovieRepositories

class UpdateMoviesUseCases(val movieRepositories: MovieRepositories) {

    suspend fun execute(): List<MovieModel> = movieRepositories.updateAllMovies()

}