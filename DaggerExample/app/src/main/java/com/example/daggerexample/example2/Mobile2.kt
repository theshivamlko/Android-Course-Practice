package com.example.daggerexample.example2

import com.example.daggerexample.example1.AmoledDisplay
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Mobile2 @Inject constructor(
    private val simCard2: SimCard2,
    private val battery2: Battery2,
    // 3rd party class
    private val amoledDisplay3rdPartyClass: AmoledDisplay3rdPartyClass,
    // interface implement
    private val display2: Display2
) {
    init {
        println("init Mobile2")

    }

    fun call() {
        println("call")
        simCard2.getNumber()
        battery2.getPercentage()
        amoledDisplay3rdPartyClass.turnOnScreen()
        display2.turnOnScreen()

    }
}