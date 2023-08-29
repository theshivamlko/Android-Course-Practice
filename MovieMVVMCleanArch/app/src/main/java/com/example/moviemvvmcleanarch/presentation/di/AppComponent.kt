package com.example.moviemvvmcleanarch.presentation.di

import com.example.moviemvvmcleanarch.presentation.di.artist.ArtistSubComponent
import com.example.moviemvvmcleanarch.presentation.di.common.CacheDataModule
import com.example.moviemvvmcleanarch.presentation.di.common.DatabaseModule
import com.example.moviemvvmcleanarch.presentation.di.common.LocalDBModule
import com.example.moviemvvmcleanarch.presentation.di.common.RemoteDataModule
import com.example.moviemvvmcleanarch.presentation.di.common.RepositoryModule
import com.example.moviemvvmcleanarch.presentation.di.common.RetrofitModule
import com.example.moviemvvmcleanarch.presentation.di.common.UseCaseModule
import com.example.moviemvvmcleanarch.presentation.di.movie.MovieSubComponent
import com.example.moviemvvmcleanarch.presentation.di.tvshow.TVShowSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CacheDataModule::class,
        DatabaseModule::class,
        LocalDBModule::class,
        RemoteDataModule::class,
        RepositoryModule::class,
        RetrofitModule::class,
        UseCaseModule::class,
    ]
)
interface AppComponent {

    fun movieSubComponent():MovieSubComponent.Factory
    fun tvShowSubComponent():TVShowSubComponent.Factory
    fun artistSubComponent():ArtistSubComponent.Factory

}