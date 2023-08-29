package com.example.moviemvvmcleanarch.presentation.di.common

import com.example.moviemvvmcleanarch.data.api.TMDBService
import com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl.ArtistRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowRemoteDataSourceImpl
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RemoteDataModule(
    val apiKey: String,

    ) {


    @Provides
    @Singleton
    fun provideRemoteDataModule(tmdbService: TMDBService): MovieRemoteDataSourceImpl {
        return MovieRemoteDataSourceImpl(tmdbService)
    }

    @Provides
    @Singleton
    fun provideTVShowDataModule(tmdbService: TMDBService): TVShowRemoteDataSourceImpl {
        return TVShowRemoteDataSourceImpl(tmdbService)
    }

    @Provides
    @Singleton
    fun provideArtistDataModule(tmdbService: TMDBService): ArtistRemoteDataSourceImpl {
        return ArtistRemoteDataSourceImpl(tmdbService)
    }

}