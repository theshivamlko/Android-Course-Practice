package com.example.moviesappretrofitroom.presentation.dependencies

import com.example.moviesappretrofitroom.data.datasource.MovieCacheDataSource
import com.example.moviesappretrofitroom.data.datasourceImpl.MovieCacheDataSourceImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class CacheDataModule {
    @Singleton
    @Provides
    fun getCacheDataSource():MovieCacheDataSource{
        return  MovieCacheDataSourceImplementation()
    }
}