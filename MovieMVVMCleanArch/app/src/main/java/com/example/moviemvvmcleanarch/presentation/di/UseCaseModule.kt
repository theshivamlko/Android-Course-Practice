package com.example.moviemvvmcleanarch.presentation.di

import com.example.moviemvvmcleanarch.data.repository.artist.ArtistRepositoryImpl
import com.example.moviemvvmcleanarch.data.repository.movie.MovieRepositoryImpl
import com.example.moviemvvmcleanarch.data.repository.movie.TVShowRepositoryImpl
import com.example.moviemvvmcleanarch.domain.usecase.GetArtistUseCase
import com.example.moviemvvmcleanarch.domain.usecase.GetMoviesUseCase
import com.example.moviemvvmcleanarch.domain.usecase.GetTVShowUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetMovieUseCase(movieRepositoryImpl: MovieRepositoryImpl): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetTVShowUseCase(tvShowRepositoryImpl: TVShowRepositoryImpl): GetTVShowUseCase {
        return GetTVShowUseCase(tvShowRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetMArtistUseCase(artistRepositoryImpl: ArtistRepositoryImpl): GetArtistUseCase {
        return GetArtistUseCase(artistRepositoryImpl)
    }

}