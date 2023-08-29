package com.example.moviemvvmcleanarch.presentation.di;


import com.example.moviemvvmcleanarch.data.repository.artist.ArtistRepositoryImpl
import com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl.ArtistCacheSourceImpl
import com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl.ArtistLocalDBSourceImpl
import com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl.ArtistRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.MovieRepositoryImpl;
import com.example.moviemvvmcleanarch.data.repository.movie.TVShowRepositoryImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieCacheDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieLocalDBDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowCacheDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowLocalDBDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowRemoteDataSourceImpl

import dagger.Module;
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepositoryModule(
        movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl,
        movieLocalDBDataSourceImpl: MovieLocalDBDataSourceImpl,
        movieCacheDataSourceImpl: MovieCacheDataSourceImpl
    ): MovieRepositoryImpl {
        return MovieRepositoryImpl(
            movieRemoteDataSourceImpl, movieLocalDBDataSourceImpl, movieCacheDataSourceImpl
        )
    }

    @Provides
    @Singleton
    fun provideTVShowRepositoryModule(
        tvShowRemoteDataSourceImpl: TVShowRemoteDataSourceImpl,
        tvShowLocalDBDataSourceImpl: TVShowLocalDBDataSourceImpl,
        tvShowCacheDataSourceImpl: TVShowCacheDataSourceImpl
    ): TVShowRepositoryImpl {
        return TVShowRepositoryImpl(
            tvShowRemoteDataSourceImpl, tvShowLocalDBDataSourceImpl, tvShowCacheDataSourceImpl
        )
    }

    @Provides
    @Singleton
    fun provideRepositoryModule(
        artistRemoteDataSourceImpl: ArtistRemoteDataSourceImpl,
        artistLocalDBSourceImpl: ArtistLocalDBSourceImpl,
        artistCacheSourceImpl: ArtistCacheSourceImpl
    ): ArtistRepositoryImpl {
        return ArtistRepositoryImpl(
            artistRemoteDataSourceImpl, artistLocalDBSourceImpl, artistCacheSourceImpl
        )
    }


}
