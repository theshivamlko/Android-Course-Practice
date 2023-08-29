package com.example.moviemvvmcleanarch.presentation.di

import com.example.moviemvvmcleanarch.presentation.di.artist.ArtistSubComponent
import com.example.moviemvvmcleanarch.presentation.di.movie.MovieSubComponent
import com.example.moviemvvmcleanarch.presentation.di.tvshow.TVShowSubComponent

interface Injector {

    fun createMovieSubComponent():MovieSubComponent
    fun createTVShowSubComponent():TVShowSubComponent
    fun createArtistSubComponent():ArtistSubComponent

}