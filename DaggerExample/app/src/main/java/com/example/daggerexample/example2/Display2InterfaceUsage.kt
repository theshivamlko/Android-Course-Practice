package com.example.daggerexample.example2

import javax.inject.Inject

class Display2InterfaceUsage @Inject constructor(): Display2 {

    init {
        println("Display2InterfaceUsage init")

    }
    override fun turnOnScreen() {
        println("Display2InterfaceUsage turnOnScreen")

    }
}