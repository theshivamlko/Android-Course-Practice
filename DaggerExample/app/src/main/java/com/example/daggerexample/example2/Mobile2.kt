package com.example.daggerexample.example2

import javax.inject.Inject

class Mobile2 @Inject constructor(
    private val simCard2: SimCard2,
    private val battery2: Battery2,
  //  private val display2: Display2
) {
    init {
        println("init Mobile2")

    }

    fun call() {
        println("call")
        simCard2.getNumber()
        battery2.getPercentage()
      //  display2.turnOnScreen()

    }
}