package com.example.moviesappretrofitroom.presentation.livedata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesappretrofitroom.domain.usecases.GetMoviesUseCases
import com.example.moviesappretrofitroom.domain.usecases.UpdateMoviesUseCases

class MovieViewModelFactory(private val getMoviesUseCases: GetMoviesUseCases,
    private val updateMoviesUseCases: UpdateMoviesUseCases
    ):ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieViewModel::class.java)){
            return  MovieViewModel(getMoviesUseCases,updateMoviesUseCases) as T
        }

        throw  IllegalAccessException("Unknown VieModel")
    }
}