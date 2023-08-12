package com.example.daggerexample

import dagger.Module
import dagger.Provides

@Module
class AmoledDisplayModule {
    @Provides
    fun provideAmoledDisplay(amoledDisplay: AmoledDisplay):Display{
        println("AmoledDisplayModule DISPLAY")
        return  AmoledDisplay()
    }
}