package com.example.moviemvvmcleanarch.presentation.di.movie

import com.example.moviemvvmcleanarch.domain.usecase.GetArtistUseCase
import com.example.moviemvvmcleanarch.domain.usecase.GetMoviesUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateArtistsUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateMoviesUseCase
import com.example.moviemvvmcleanarch.presentation.artist.ArtistViewModel
import com.example.moviemvvmcleanarch.presentation.movie.MovieViewModel
import dagger.Module
import dagger.Provides


@Module
class MovieViewModelModule {


    @MovieScope
    @Provides
    fun provideMovieViewModel(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ): MovieViewModel {
        return MovieViewModel(getMoviesUseCase, updateMoviesUseCase)
    }

}