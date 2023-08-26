package com.example.daggerexample.example1

import dagger.Module
import dagger.Provides

@Module
class AmoledDisplayModule(val resolution:Int) {
    @Provides
    fun provideAmoledDisplay(amoledDisplay: AmoledDisplay): Display {
        println("AmoledDisplayModule DISPLAY $resolution")
        return  AmoledDisplay()
    }
}