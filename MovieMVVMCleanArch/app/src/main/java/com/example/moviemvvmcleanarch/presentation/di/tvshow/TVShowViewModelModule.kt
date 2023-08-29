package com.example.moviemvvmcleanarch.presentation.di.tvshow

import com.example.moviemvvmcleanarch.domain.usecase.GetArtistUseCase
import com.example.moviemvvmcleanarch.domain.usecase.GetTVShowUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateArtistsUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateMoviesUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateTVShowUseCase
import com.example.moviemvvmcleanarch.presentation.artist.ArtistViewModel
import com.example.moviemvvmcleanarch.presentation.tvshow.TvShowViewModel
import dagger.Module
import dagger.Provides


@Module
class TVShowViewModelModule {


    @TVShowScope
    @Provides
    fun provideTVShowViewModel(
        getTVShowUseCase: GetTVShowUseCase,
        updateTVShowUseCase: UpdateTVShowUseCase
    ): TvShowViewModel {
        return TvShowViewModel(getTVShowUseCase, updateTVShowUseCase)
    }

}