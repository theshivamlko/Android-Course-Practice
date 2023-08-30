package com.example.moviemvvmcleanarch.presentation.di.common

import com.example.moviemvvmcleanarch.data.repository.artist.datasource.IArtistLocalDBSource
import com.example.moviemvvmcleanarch.data.repository.artist.datasourceImpl.ArtistLocalDBSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieLocalDBDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.ITVShowLocalDBDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieLocalDBDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowLocalDBDataSourceImpl
import com.example.moviemvvmcleanarch.data.roomdb.ArtistDAO
import com.example.moviemvvmcleanarch.data.roomdb.MovieDAO
import com.example.moviemvvmcleanarch.data.roomdb.TVShowDAO
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class LocalDBModule() {


    @Provides
    @Singleton
    fun provideMovieLocalDB(movieDAO: MovieDAO):IMovieLocalDBDataSource{
        return  MovieLocalDBDataSourceImpl(movieDAO)
    }

    @Provides
    @Singleton
    fun provideTVShowLocalDB(tvShowDAO: TVShowDAO):ITVShowLocalDBDataSource{
        return  TVShowLocalDBDataSourceImpl(tvShowDAO)
    }

    @Provides
    @Singleton
    fun provideArtistLocalDB(artistDAO: ArtistDAO):IArtistLocalDBSource{
        return  ArtistLocalDBSourceImpl(artistDAO)
    }
}