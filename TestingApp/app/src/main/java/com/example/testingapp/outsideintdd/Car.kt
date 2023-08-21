package com.example.testingapp.outsideintdd


class Car(
    var fuel: Double,
    var engine:Engine
) {

    fun turnOn() {
        fuel -= 0.5
        engine.turnOn()
    }
}