package com.example.moviemvvmcleanarch.presentation.di

import android.content.Context
import com.example.moviemvvmcleanarch.presentation.di.artist.ArtistSubComponent
import com.example.moviemvvmcleanarch.presentation.di.movie.MovieSubComponent
import com.example.moviemvvmcleanarch.presentation.di.tvshow.TVShowSubComponent
import dagger.Module


@Module(subcomponents = [
    MovieSubComponent::class,
    TVShowSubComponent::class,
    ArtistSubComponent::class,

])
class AppModule(val context: Context) {


    fun provideApplicationContext():Context{
        return context.applicationContext
    }

}