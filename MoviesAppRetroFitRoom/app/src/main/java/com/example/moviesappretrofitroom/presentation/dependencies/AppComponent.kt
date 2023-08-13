package com.example.moviesappretrofitroom.presentation.dependencies

import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class,CacheDataModule::class,RoomDBModule::class,
LocalDataModule::class,RetrofitModule::class,RemoteDataModule::class,
 RepositoryModule::class,UseCaseModule::class])
interface AppComponent {
    fun movieSubComponent():MovieSubComponent.Factory
}