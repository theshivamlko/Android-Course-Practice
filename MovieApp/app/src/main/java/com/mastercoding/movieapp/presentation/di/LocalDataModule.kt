package com.mastercoding.movieapp.presentation.di

import com.mastercoding.movieapp.data.datasource.MovieLocalDataSource
import com.mastercoding.movieapp.data.datasourceImpl.MovieLocalDataSourceImpl
import com.mastercoding.movieapp.data.db.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao):MovieLocalDataSource{
        return MovieLocalDataSourceImpl(movieDao)
    }

}