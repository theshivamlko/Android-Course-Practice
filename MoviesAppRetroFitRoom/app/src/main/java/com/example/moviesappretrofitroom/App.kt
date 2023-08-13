package com.example.moviesappretrofitroom

import android.app.Application
import com.example.moviesappretrofitroom.presentation.dependencies.AppComponent
import com.example.moviesappretrofitroom.presentation.dependencies.AppModule
import com.example.moviesappretrofitroom.presentation.dependencies.CacheDataModule
import com.example.moviesappretrofitroom.presentation.dependencies.DaggerAppComponent
import com.example.moviesappretrofitroom.presentation.dependencies.Injector
import com.example.moviesappretrofitroom.presentation.dependencies.MovieSubComponent
import com.example.moviesappretrofitroom.presentation.dependencies.RemoteDataModule
import com.example.moviesappretrofitroom.presentation.dependencies.RetrofitModule

class App:Application(),Injector {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
       appComponent=DaggerAppComponent.builder()
           .appModule(AppModule(applicationContext))
           .retrofitModule(RetrofitModule(BuildConfig.BASE_URL))
           .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY)).build()
    }

    override fun createMovieSubComponent(): MovieSubComponent {
        return appComponent.movieSubComponent().create()
    }


}