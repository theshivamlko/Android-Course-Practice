package com.example.moviemvvmcleanarch.presentation.di.artist

import com.example.moviemvvmcleanarch.data.model.Artist
import com.example.moviemvvmcleanarch.presentation.MainActivity
import dagger.Subcomponent


@ArtistScope
@Subcomponent(modules = [ArtistViewModelModule::class])
interface ArtistSubComponent {

    fun inject(mainActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create():ArtistSubComponent

    }
}