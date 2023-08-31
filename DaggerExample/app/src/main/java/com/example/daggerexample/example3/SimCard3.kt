package com.example.daggerexample.example2

import javax.inject.Inject


class SimCard3 @Inject constructor(private val serviceProvider2: ServiceProvider3) {
    init {
        println("init SimCard2")

    }

    fun getNumber() {
        println("getNumber")
        serviceProvider2.getNetworkProvider()

    }
}