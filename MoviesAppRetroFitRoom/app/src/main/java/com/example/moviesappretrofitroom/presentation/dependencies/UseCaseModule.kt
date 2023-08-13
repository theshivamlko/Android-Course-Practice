package com.example.moviesappretrofitroom.presentation.dependencies

import com.example.moviesappretrofitroom.domain.repositories.MovieRepositories
import com.example.moviesappretrofitroom.domain.usecases.GetMoviesUseCases
import com.example.moviesappretrofitroom.domain.usecases.UpdateMoviesUseCases
import dagger.Module
import dagger.Provides


@Module
class UseCaseModule {

    @Provides
    fun provideGetMovieUseCase(movieRepositories: MovieRepositories):GetMoviesUseCases{
        return  GetMoviesUseCases(movieRepositories)
    }

    @Provides
    fun provideUpdateMovieUseCase(movieRepositories: MovieRepositories):UpdateMoviesUseCases{
        return UpdateMoviesUseCases(movieRepositories)
    }

}