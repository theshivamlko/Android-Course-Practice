package com.example.moviemvvmcleanarch.domain.usecase

import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.domain.repository.IMovieRepository

class UpdateMoviesUseCase(private val movieRepository: IMovieRepository) {

    suspend fun updateMoviesList(moviesList:List<Movie>) = movieRepository.updatePopularMovies(moviesList)

}