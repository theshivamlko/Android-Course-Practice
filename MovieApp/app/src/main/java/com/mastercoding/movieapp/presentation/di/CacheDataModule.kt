package com.mastercoding.movieapp.presentation.di

import com.mastercoding.movieapp.data.datasource.MovieCacheDataSource
import com.mastercoding.movieapp.data.datasourceImpl.MovieCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class CacheDataModule {


    @Singleton
    @Provides
    fun provideMovieCacheDataSource(): MovieCacheDataSource{
        return MovieCacheDataSourceImpl()
    }
}