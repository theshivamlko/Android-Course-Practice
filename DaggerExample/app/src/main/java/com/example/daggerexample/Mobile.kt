package com.example.daggerexample

import javax.inject.Inject

class Mobile @Inject constructor(private val simCard: SimCard,private val battery: Battery) {
    init {

    }

    fun call() {

        simCard.getNumber()
        battery.getPercentage()

    }
}