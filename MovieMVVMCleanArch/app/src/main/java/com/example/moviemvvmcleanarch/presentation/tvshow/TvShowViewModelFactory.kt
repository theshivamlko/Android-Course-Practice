package com.example.moviemvvmcleanarch.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviemvvmcleanarch.domain.usecase.GetTVShowUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateTVShowUseCase

class TvShowViewModelFactory(
    val getTVShowUseCase: GetTVShowUseCase,
    val updateTVShowUseCase: UpdateTVShowUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TvShowViewModel(getTVShowUseCase,updateTVShowUseCase) as T
    }
}