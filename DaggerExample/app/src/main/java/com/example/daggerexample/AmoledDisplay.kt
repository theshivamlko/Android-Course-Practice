package com.example.daggerexample

import javax.inject.Inject


class AmoledDisplay @Inject constructor() : Display {
    override fun turnOnScreen() {
        println("Mobile Amoled DISPLAY")
    }
}