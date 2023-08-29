package com.example.moviemvvmcleanarch.domain.usecase

import com.example.moviemvvmcleanarch.data.model.MovieList
import com.example.moviemvvmcleanarch.domain.repository.IMovieRepository
import retrofit2.Response

class GetMoviesUseCase(private val movieRepository: IMovieRepository) {

    suspend fun getMoviesList(): Response<MovieList> = movieRepository.getPopularMovies()

}