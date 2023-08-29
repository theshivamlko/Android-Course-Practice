package com.example.moviemvvmcleanarch.presentation.di

import android.content.Context
import dagger.Module


@Module
class AppModule(val context: Context) {


    fun provideApplicationContext():Context{
        return context.applicationContext
    }

}