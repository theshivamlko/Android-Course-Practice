package com.example.daggerexample.example2



// Consider this as 3rd party class
// where we cannot inject Annotations
class AmoledDisplay3rdPartyClass3 : Display3{
    var displayInt=0

    init {
        println("AmoledDisplay3rdPartyClass3")

    }
    override fun turnOnScreen() {
        println("AmoledDisplay3rdPartyClass3 turnOnScreen $displayInt")
    }
}