package com.example.moviemvvmcleanarch.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviemvvmcleanarch.domain.usecase.GetArtistUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateArtistsUseCase

class ArtistViewModelFactory(

    val getArtistUseCase: GetArtistUseCase,
    val updateArtistsUseCase: UpdateArtistsUseCase

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArtistViewModel(getArtistUseCase,updateArtistsUseCase) as T
    }
}