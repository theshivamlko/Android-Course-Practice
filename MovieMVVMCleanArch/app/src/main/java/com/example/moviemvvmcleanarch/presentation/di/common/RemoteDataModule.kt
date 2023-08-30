package com.example.moviemvvmcleanarch.presentation.di.common

import com.example.moviemvvmcleanarch.data.api.TMDBService
import com.example.moviemvvmcleanarch.data.repository.artist.datasource.IArtistRemoteDataSource
import com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl.ArtistRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieRemoteDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.ITVShowRemoteDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowRemoteDataSourceImpl
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RemoteDataModule(
    val apiKey: String
) {


    @Provides
    @Singleton
    fun provideRemoteDataModule(tmdbService: TMDBService): IMovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(tmdbService)
    }

    @Provides
    @Singleton
    fun provideTVShowDataModule(tmdbService: TMDBService): ITVShowRemoteDataSource {
        return TVShowRemoteDataSourceImpl(tmdbService)
    }

    @Provides
    @Singleton
    fun provideArtistDataModule(tmdbService: TMDBService): IArtistRemoteDataSource {
        return ArtistRemoteDataSourceImpl(tmdbService)
    }

}