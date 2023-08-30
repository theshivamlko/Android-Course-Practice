package com.example.moviemvvmcleanarch.presentation.di

import android.app.Application
import com.example.moviemvvmcleanarch.BuildConfig
import com.example.moviemvvmcleanarch.presentation.di.artist.ArtistSubComponent
import com.example.moviemvvmcleanarch.presentation.di.common.RemoteDataModule
import com.example.moviemvvmcleanarch.presentation.di.common.RetrofitModule
import com.example.moviemvvmcleanarch.presentation.di.movie.MovieSubComponent
import com.example.moviemvvmcleanarch.presentation.di.tvshow.TVShowSubComponent
import javax.inject.Inject

class MyApplication : Application(), Injector {


    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .retrofitModule(RetrofitModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule("API_KEY"))
            .build()

    }


    override fun createMovieSubComponent(): MovieSubComponent {
        return appComponent.movieSubComponent().create()
    }

    override fun createTVShowSubComponent(): TVShowSubComponent {
        return appComponent.tvShowSubComponent().create()
    }

    override fun createArtistSubComponent(): ArtistSubComponent {
        return appComponent.artistSubComponent().create()
    }
}