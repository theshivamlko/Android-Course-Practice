package com.example.testingapp.outsideintdd

class Engine(
    var isTurnOn: Boolean,
    var temperature:Int

) {

    fun turnOn() {
        isTurnOn = true
        temperature=95

    }

}