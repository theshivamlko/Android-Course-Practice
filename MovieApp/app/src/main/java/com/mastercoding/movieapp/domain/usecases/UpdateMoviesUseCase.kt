package com.mastercoding.movieapp.domain.usecases

import com.mastercoding.movieapp.data.model.Movie
import com.mastercoding.movieapp.domain.repository.MovieRepository

class UpdateMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(): List<Movie>? = movieRepository.updateMovies()
}



