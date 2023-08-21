package com.example.testingapp.outsideintdd

class Engine(
    var isTurnOn: Boolean,
    var temperature:Int

) {

    fun turnOn() {
        isTurnOn = true
        temperature=95

    }
   suspend fun turnOnViaSuspend() {
        isTurnOn = true
        temperature=90

    }

}