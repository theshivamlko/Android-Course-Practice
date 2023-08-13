package com.example.moviesappretrofitroom.presentation.dependencies

import com.example.moviesappretrofitroom.domain.usecases.GetMoviesUseCases
import com.example.moviesappretrofitroom.domain.usecases.UpdateMoviesUseCases
import com.example.moviesappretrofitroom.presentation.livedata.MovieViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class MovieModule {

    @MovieScoop
    @Provides
    fun provideMovieViewModelFactory(getMoviesUseCases: GetMoviesUseCases
                                     ,updateMoviesUseCases: UpdateMoviesUseCases): MovieViewModelFactory {

        return MovieViewModelFactory(getMoviesUseCases,updateMoviesUseCases)
    }
}