package com.example.moviesappretrofitroom.presentation.dependencies

import com.example.moviesappretrofitroom.data.datasource.MovieLocalDataSource
import com.example.moviesappretrofitroom.data.datasourceImpl.MovieLocalDataSourceImplementation
import com.example.moviesappretrofitroom.data.roomdb.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(  movieDao: MovieDao ):MovieLocalDataSource{
        return MovieLocalDataSourceImplementation(movieDao)
    }



}