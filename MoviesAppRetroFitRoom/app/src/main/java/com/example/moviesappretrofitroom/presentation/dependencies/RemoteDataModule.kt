package com.example.moviesappretrofitroom.presentation.dependencies

import com.example.moviesappretrofitroom.data.api.TMDBService
import com.example.moviesappretrofitroom.data.datasource.MovieRemoteDataSource
import com.example.moviesappretrofitroom.data.datasourceImpl.MovieRemoteDataSourceImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RemoteDataModule(val apiKey:String) {

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(  tmdbService: TMDBService):MovieRemoteDataSource{
        return  MovieRemoteDataSourceImplementation(tmdbService,apiKey)
    }

}