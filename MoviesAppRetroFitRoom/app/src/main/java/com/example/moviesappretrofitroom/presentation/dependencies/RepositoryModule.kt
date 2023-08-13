package com.example.moviesappretrofitroom.presentation.dependencies

import com.example.moviesappretrofitroom.data.MovieRepositoriesImpl
import com.example.moviesappretrofitroom.data.datasource.MovieCacheDataSource
import com.example.moviesappretrofitroom.data.datasource.MovieLocalDataSource
import com.example.moviesappretrofitroom.data.datasource.MovieRemoteDataSource
import com.example.moviesappretrofitroom.domain.repositories.MovieRepositories
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource, movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ): MovieRepositories {

        return MovieRepositoriesImpl(
            movieRemoteDataSource,
            movieLocalDataSource,
            movieCacheDataSource
        )
    }

}