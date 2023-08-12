package com.example.daggerexample

import javax.inject.Inject


class SimCard @Inject constructor(private val serviceProvider: ServiceProvider) {
    init {

    }

    fun getNumber() {
        serviceProvider.getNetworkProvider()

    }
}