package com.example.daggerexample.example2

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Mobile3 @Inject constructor(
    private val simCard2: SimCard3,
    private val battery2: Battery3,

    // 3rd party class
    private val amoledDisplay3RdPartyClass3: AmoledDisplay3rdPartyClass3,
    // interface implement
    private val display2: Display3
) {
    init {
        println("init Mobile2")

    }

    fun call() {
        println("call")
        simCard2.getNumber()
        battery2.getPercentage()
        amoledDisplay3RdPartyClass3.turnOnScreen()
        display2.turnOnScreen()

    }
}