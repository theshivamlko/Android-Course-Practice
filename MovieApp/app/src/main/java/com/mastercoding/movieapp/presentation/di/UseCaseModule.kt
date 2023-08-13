package com.mastercoding.movieapp.presentation.di

import com.mastercoding.movieapp.data.MovieRepositoryImpl
import com.mastercoding.movieapp.domain.repository.MovieRepository
import com.mastercoding.movieapp.domain.usecases.GetMoviesUseCase
import com.mastercoding.movieapp.domain.usecases.UpdateMoviesUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository):GetMoviesUseCase{
        return GetMoviesUseCase(movieRepository)
    }

    @Provides
    fun provideUpdateMovieUseCase(movieRepository: MovieRepository):UpdateMoviesUseCase{
        return UpdateMoviesUseCase(movieRepository)
    }

}