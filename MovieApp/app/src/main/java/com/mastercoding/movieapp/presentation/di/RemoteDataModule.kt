package com.mastercoding.movieapp.presentation.di

import com.mastercoding.movieapp.data.api.TMDBService
import com.mastercoding.movieapp.data.datasource.MovieRemoteDataSource
import com.mastercoding.movieapp.data.datasourceImpl.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val apiKey: String) {

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(tmdbService: TMDBService):MovieRemoteDataSource{
        return MovieRemoteDataSourceImpl(
            tmdbService,
            apiKey
        )
    }

}