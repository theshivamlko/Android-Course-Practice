package com.example.daggerexample.example2



// Consider this as 3rd party class
// where we cannot inject Annotations
class AmoledDisplay3rdPartyClass : Display2 {

    init {
        println("AmoledDisplay3rdPartyClass")

    }
    override fun turnOnScreen() {
        println("Mobile2 AmoledDisplay3rdPartyClass")
    }
}