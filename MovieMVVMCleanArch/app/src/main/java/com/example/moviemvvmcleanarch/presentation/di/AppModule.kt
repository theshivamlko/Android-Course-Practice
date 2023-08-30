package com.example.moviemvvmcleanarch.presentation.di

import android.content.Context
import com.example.moviemvvmcleanarch.presentation.di.artist.ArtistSubComponent
import com.example.moviemvvmcleanarch.presentation.di.movie.MovieSubComponent
import com.example.moviemvvmcleanarch.presentation.di.tvshow.TVShowSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(subcomponents = [
    MovieSubComponent::class,
    TVShowSubComponent::class,
    ArtistSubComponent::class,

])
class AppModule(val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext():Context{
        return context.applicationContext
    }

}