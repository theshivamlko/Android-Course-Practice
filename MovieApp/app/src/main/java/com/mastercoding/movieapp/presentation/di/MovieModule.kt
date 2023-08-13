package com.mastercoding.movieapp.presentation.di

import com.mastercoding.movieapp.domain.usecases.GetMoviesUseCase
import com.mastercoding.movieapp.domain.usecases.UpdateMoviesUseCase
import com.mastercoding.movieapp.presentation.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {
    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ): ViewModelFactory{

        return ViewModelFactory(
            getMoviesUseCase,
            updateMoviesUseCase
        )
    }
}