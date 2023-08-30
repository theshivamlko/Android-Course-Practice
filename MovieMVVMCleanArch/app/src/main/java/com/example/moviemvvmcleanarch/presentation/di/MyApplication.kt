package com.example.moviemvvmcleanarch.presentation.di

import android.app.Application


class MyApplication : Application()/*, Injector */{


   // lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
       /* appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .retrofitModule(RetrofitModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule("API_KEY"))
            .build()*/

    }


/*    override fun createMovieSubComponent(): MovieSubComponent {
        return appComponent.movieSubComponent().create()
    }

    override fun createTVShowSubComponent(): TVShowSubComponent {
        return appComponent.tvShowSubComponent().create()
    }

    override fun createArtistSubComponent(): ArtistSubComponent {
        return appComponent.artistSubComponent().create()
    }*/
}