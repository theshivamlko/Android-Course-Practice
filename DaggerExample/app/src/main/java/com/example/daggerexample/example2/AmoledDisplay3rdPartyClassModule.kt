package com.example.daggerexample.example2

import dagger.Module
import dagger.Provides

@Module
class AmoledDisplay3rdPartyClassModule {

    @Provides
    fun getInstance():AmoledDisplay3rdPartyClass{
        return  AmoledDisplay3rdPartyClass()
    }

}