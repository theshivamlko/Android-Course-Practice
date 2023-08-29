package com.example.moviemvvmcleanarch.presentation.di

import android.app.Application
import com.example.moviemvvmcleanarch.presentation.di.artist.ArtistSubComponent
import com.example.moviemvvmcleanarch.presentation.di.movie.MovieSubComponent
import com.example.moviemvvmcleanarch.presentation.di.tvshow.TVShowSubComponent

class MyApplication:Application(),Injector {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

    }


    override fun createMovieSubComponent(): MovieSubComponent {
        TODO("Not yet implemented")
    }

    override fun createTVShowSubComponent(): TVShowSubComponent {
        TODO("Not yet implemented")
    }

    override fun createArtistSubComponent(): ArtistSubComponent {
        TODO("Not yet implemented")
    }
}