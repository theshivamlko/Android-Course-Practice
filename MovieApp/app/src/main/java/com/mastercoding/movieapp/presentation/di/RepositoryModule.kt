package com.mastercoding.movieapp.presentation.di

import com.mastercoding.movieapp.data.MovieRepositoryImpl
import com.mastercoding.movieapp.data.datasource.MovieCacheDataSource
import com.mastercoding.movieapp.data.datasource.MovieLocalDataSource
import com.mastercoding.movieapp.data.datasource.MovieRemoteDataSource
import com.mastercoding.movieapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource

    ):MovieRepository{

        return MovieRepositoryImpl(
            movieRemoteDataSource,
            movieLocalDataSource,
            movieCacheDataSource
        )

    }

}