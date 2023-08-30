package com.example.moviemvvmcleanarch.presentation.di.common;


import com.example.moviemvvmcleanarch.data.repository.artist.ArtistRepositoryImpl
import com.example.moviemvvmcleanarch.data.repository.artist.datasource.IArtistCacheSource
import com.example.moviemvvmcleanarch.data.repository.artist.datasource.IArtistLocalDBSource
import com.example.moviemvvmcleanarch.data.repository.artist.datasource.IArtistRemoteDataSource
import com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl.ArtistCacheSourceImpl
import com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl.ArtistLocalDBSourceImpl
import com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl.ArtistRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.MovieRepositoryImpl;
import com.example.moviemvvmcleanarch.data.repository.movie.TVShowRepositoryImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieCacheDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieLocalDBDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieRemoteDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.ITVShowCacheDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.ITVShowLocalDBDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.ITVShowRemoteDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieCacheDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieLocalDBDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowCacheDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowLocalDBDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.domain.repository.IArtistRepository
import com.example.moviemvvmcleanarch.domain.repository.IMovieRepository
import com.example.moviemvvmcleanarch.domain.repository.ITVShowsRepository

import dagger.Module;
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepositoryModule(
        iMovieRemoteDataSource: IMovieRemoteDataSource,
        iMovieLocalDBDataSource: IMovieLocalDBDataSource,
        iMovieCacheDataSource: IMovieCacheDataSource
    ): IMovieRepository {
        return MovieRepositoryImpl(
            iMovieRemoteDataSource, iMovieLocalDBDataSource, iMovieCacheDataSource
        )
    }

    @Provides
    @Singleton
    fun provideTVShowRepositoryModule(
        itvShowRemoteDataSource: ITVShowRemoteDataSource,
        itvShowLocalDBDataSource: ITVShowLocalDBDataSource,
       itvShowCacheDataSource: ITVShowCacheDataSource
    ): ITVShowsRepository {
        return TVShowRepositoryImpl(
            itvShowRemoteDataSource, itvShowLocalDBDataSource, itvShowCacheDataSource
        )
    }

    @Provides
    @Singleton
    fun provideRepositoryModule(
         iArtistRemoteDataSource: IArtistRemoteDataSource,
       iArtistLocalDBSource: IArtistLocalDBSource,
      iArtistCacheSource: IArtistCacheSource
    ): IArtistRepository {
        return ArtistRepositoryImpl(
            iArtistRemoteDataSource, iArtistLocalDBSource, iArtistCacheSource
        )
    }


}
