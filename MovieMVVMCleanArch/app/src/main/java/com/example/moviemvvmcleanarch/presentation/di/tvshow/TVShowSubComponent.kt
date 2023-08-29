package com.example.moviemvvmcleanarch.presentation.di.tvshow

import com.example.moviemvvmcleanarch.presentation.MainActivity
import dagger.Subcomponent


@TVShowScope
@Subcomponent(modules = [TVShowViewModelModule::class])
interface TVShowSubComponent {

    fun inject(mainActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create():TVShowSubComponent

    }
}