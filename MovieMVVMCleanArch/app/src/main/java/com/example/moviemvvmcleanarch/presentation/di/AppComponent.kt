package com.example.moviemvvmcleanarch.presentation.di

import dagger.Component


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
}