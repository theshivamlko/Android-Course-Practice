package com.example.daggerexample.example2

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AmoledDisplay3rdPartyClassModule {

    var counter = 0

    constructor(count: Int) {
        counter = count
        println("AmoledDisplay3rdPartyClassModule Param==========$counter")
    }


    @Provides
    fun getInstance( ): AmoledDisplay3rdPartyClass {
        return AmoledDisplay3rdPartyClass()
    }
 /*   @Provides
    fun getInstanceParams( ): AmoledDisplay3rdPartyClass {
        return AmoledDisplay3rdPartyClass(counter)
    }*/

}