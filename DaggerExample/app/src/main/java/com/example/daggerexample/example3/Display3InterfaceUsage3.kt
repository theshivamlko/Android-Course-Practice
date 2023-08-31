package com.example.daggerexample.example2

import javax.inject.Inject

class Display3InterfaceUsage3 @Inject constructor(): Display2 {

    init {
        println("Display3InterfaceUsage init")

    }
    override fun turnOnScreen() {
        println("Display3InterfaceUsage turnOnScreen")

    }
}