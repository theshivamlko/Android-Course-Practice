package com.example.moviesappretrofitroom.presentation.dependencies

import com.example.moviesappretrofitroom.data.api.TMDBService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RetrofitModule(private val baseURL:String) {
    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
        return  Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseURL).build()
    }


    @Singleton
    @Provides
    fun providesTMDBService(retrofit: Retrofit):TMDBService{
        return retrofit.create(TMDBService::class.java)
    }

}