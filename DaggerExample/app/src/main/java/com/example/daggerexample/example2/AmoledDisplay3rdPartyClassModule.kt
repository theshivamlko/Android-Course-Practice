package com.example.daggerexample.example2

import dagger.Module
import dagger.Provides

@Module
class AmoledDisplay3rdPartyClassModule {

    var counter = 0

    constructor(count: Int) {
        counter = count
        println("AmoledDisplay3rdPartyClassModule Param==========$counter")
    }


    @Provides
    fun getInstance(): AmoledDisplay3rdPartyClass {
        return AmoledDisplay3rdPartyClass()
    }

}