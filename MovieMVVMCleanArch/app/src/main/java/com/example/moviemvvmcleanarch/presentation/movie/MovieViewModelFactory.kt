package com.example.moviemvvmcleanarch.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviemvvmcleanarch.domain.usecase.GetMoviesUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateMoviesUseCase
import javax.inject.Inject

class MovieViewModelFactory   @Inject constructor(
    val getMoviesUseCase: GetMoviesUseCase,
    val updateMoviesUseCase: UpdateMoviesUseCase
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(getMoviesUseCase, updateMoviesUseCase) as T
    }
}