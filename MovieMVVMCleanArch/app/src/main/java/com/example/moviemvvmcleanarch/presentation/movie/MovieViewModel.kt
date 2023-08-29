package com.example.moviemvvmcleanarch.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.moviemvvmcleanarch.data.model.Movie
import com.example.moviemvvmcleanarch.domain.usecase.GetMoviesUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateMoviesUseCase

class MovieViewModel(
    val getMoviesUseCase: GetMoviesUseCase,

    val updateMoviesUseCase: UpdateMoviesUseCase
) : ViewModel() {

    fun getMovies() = liveData<List<Movie>> {
        val movieList = getMoviesUseCase.getMoviesList()

    }

    fun updateMovies(): LiveData<List<Movie>> {
        return liveData<List<Movie>> {
            val movieList = updateMoviesUseCase.refreshPopularMovies()
            emit(movieList)
        }
    }

}