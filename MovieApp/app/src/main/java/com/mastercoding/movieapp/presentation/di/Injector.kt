package com.mastercoding.movieapp.presentation.di

interface Injector {
    fun createMovieSubComponent(): MovieSubComponent
}