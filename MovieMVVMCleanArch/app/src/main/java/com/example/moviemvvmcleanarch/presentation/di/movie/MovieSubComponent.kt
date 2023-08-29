package com.example.moviemvvmcleanarch.presentation.di.movie

import com.example.moviemvvmcleanarch.presentation.MainActivity
import dagger.Subcomponent


@MovieScope
@Subcomponent(modules = [MovieViewModelModule::class])
interface MovieSubComponent {

    fun inject(mainActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create():MovieSubComponent

    }
}