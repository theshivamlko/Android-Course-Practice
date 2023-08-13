package com.example.moviesappretrofitroom.presentation.livedata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.moviesappretrofitroom.data.models.MovieModel
import com.example.moviesappretrofitroom.domain.usecases.GetMoviesUseCases
import com.example.moviesappretrofitroom.domain.usecases.UpdateMoviesUseCases

class MovieViewModel(
    private val getMoviesUseCases: GetMoviesUseCases,
    private val updateMoviesUseCases: UpdateMoviesUseCases

):ViewModel() {

    fun getMovies()= liveData<List<MovieModel>> {
        val movieList=getMoviesUseCases.execute()
        emit(movieList)
    }

    fun updateMovies()= liveData<List<MovieModel>> {
        val movieList=updateMoviesUseCases.execute()
        emit(movieList)
    }
}