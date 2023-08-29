package com.example.moviemvvmcleanarch.domain.usecase

import com.example.moviemvvmcleanarch.domain.repository.IMovieRepository

class DeleteMoviesUseCase(private val movieRepository: IMovieRepository) {

    suspend fun deleteMoviesList() = movieRepository.deletePopularMovies()

}