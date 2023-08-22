package com.example.testingapp.playlisttest

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
@InstallIn(FragmentComponent::class)
class PlayListModule {
    @Provides
    fun initAPI(retrofit: Retrofit): API {
        return retrofit.create(API::class.java)
    }
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://64e3a922bac46e480e791059.mockapi.io/api/v1/")
            .client(OkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

}