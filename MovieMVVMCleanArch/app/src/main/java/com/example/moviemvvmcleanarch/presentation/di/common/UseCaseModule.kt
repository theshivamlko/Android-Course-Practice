package com.example.moviemvvmcleanarch.presentation.di.common

import com.example.moviemvvmcleanarch.data.repository.artist.ArtistRepositoryImpl
import com.example.moviemvvmcleanarch.data.repository.movie.MovieRepositoryImpl
import com.example.moviemvvmcleanarch.data.repository.movie.TVShowRepositoryImpl
import com.example.moviemvvmcleanarch.domain.repository.IArtistRepository
import com.example.moviemvvmcleanarch.domain.repository.IMovieRepository
import com.example.moviemvvmcleanarch.domain.repository.ITVShowsRepository
import com.example.moviemvvmcleanarch.domain.usecase.GetArtistUseCase
import com.example.moviemvvmcleanarch.domain.usecase.GetMoviesUseCase
import com.example.moviemvvmcleanarch.domain.usecase.GetTVShowUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateArtistsUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateMoviesUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateTVShowUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetMovieUseCase(iMovieRepository: IMovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(iMovieRepository)
    }

    @Provides
    @Singleton
    fun provideGetTVShowUseCase(itvShowsRepository: ITVShowsRepository): GetTVShowUseCase {
        return GetTVShowUseCase(itvShowsRepository)
    }

    @Provides
    @Singleton
    fun provideGetMArtistUseCase(iArtistRepository: IArtistRepository): GetArtistUseCase {
        return GetArtistUseCase(iArtistRepository)
    }
    @Provides
    @Singleton
    fun provideUpdateMovieUseCase(iMovieRepository: IMovieRepository): UpdateMoviesUseCase {
        return UpdateMoviesUseCase(iMovieRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateTVShowUseCase(itvShowsRepository: ITVShowsRepository): UpdateTVShowUseCase {
        return UpdateTVShowUseCase(itvShowsRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateMArtistUseCase(iArtistRepository: IArtistRepository): UpdateArtistsUseCase {
        return UpdateArtistsUseCase(iArtistRepository)
    }

}