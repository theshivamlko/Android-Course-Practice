package com.example.daggerexample.example1

import javax.inject.Inject

class Mobile @Inject constructor(private val simCard: SimCard
                                 , private val battery: Battery, private val display: Display
) {
    init {

    }

    fun call() {
        simCard.getNumber()
        battery.getPercentage()
        display.turnOnScreen()

    }
}