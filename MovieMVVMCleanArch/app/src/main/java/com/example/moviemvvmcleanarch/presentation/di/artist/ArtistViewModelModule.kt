package com.example.moviemvvmcleanarch.presentation.di.artist

import androidx.lifecycle.ViewModelProvider
import com.example.moviemvvmcleanarch.domain.usecase.GetArtistUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateArtistsUseCase
import com.example.moviemvvmcleanarch.presentation.artist.ArtistViewModel
import com.example.moviemvvmcleanarch.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ArtistViewModelModule {


    @ArtistScope
    @Provides
    fun provideArtistViewModelFactory(
        getArtistUseCase: GetArtistUseCase, updateArtistsUseCase: UpdateArtistsUseCase
    ): ArtistViewModelFactory {
        return ArtistViewModelFactory(getArtistUseCase, updateArtistsUseCase)
    }

}