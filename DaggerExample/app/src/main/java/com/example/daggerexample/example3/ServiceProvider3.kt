package com.example.daggerexample.example2

import javax.inject.Inject

class ServiceProvider3  @Inject constructor() {

    init {

    }

    fun getNetworkProvider(){
        println("getNetworkProvider")

    }
}