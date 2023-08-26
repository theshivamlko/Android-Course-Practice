package com.example.daggerexample.example1

import javax.inject.Inject


class AmoledDisplay @Inject constructor() : Display {
    override fun turnOnScreen() {
        println("Mobile2 Amoled DISPLAY")
    }
}