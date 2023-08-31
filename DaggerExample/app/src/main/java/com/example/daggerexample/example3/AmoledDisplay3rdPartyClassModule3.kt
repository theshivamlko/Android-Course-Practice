package com.example.daggerexample.example2

import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
class AmoledDisplay3rdPartyClassModule3 {

    var counter = 0

    constructor(count: Int) {
        counter = count
        println("AmoledDisplay3rdPartyClassModule Param==========$counter")
    }


    @Provides
    fun getInstance( ): AmoledDisplay3rdPartyClass3 {
        return AmoledDisplay3rdPartyClass3()
    }
 /*   @Provides
    fun getInstanceParams( ): AmoledDisplay3rdPartyClass3 {
        return AmoledDisplay3rdPartyClass3(counter)
    }*/

}