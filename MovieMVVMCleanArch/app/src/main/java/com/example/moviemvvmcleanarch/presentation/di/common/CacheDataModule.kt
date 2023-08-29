package com.example.moviemvvmcleanarch.presentation.di.common

import com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl.ArtistCacheSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieCacheDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class CacheDataModule {

    @Provides
    @Singleton
    fun provideMovieCacheDataSource(): MovieCacheDataSourceImpl {
        return MovieCacheDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideTVShowCacheDataSource(): TVShowCacheDataSourceImpl {
        return TVShowCacheDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideArtistCacheDataSource(): ArtistCacheSourceImpl {
        return ArtistCacheSourceImpl()
    }

}