package com.example.testingapp.unittest

import com.example.testingapp.outsideintdd.Car
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CarShould {

    val car = Car(5.0)

    @Test
    fun loosingFuelWhenItTurnsOn() {

        car.turnOn()

        assertEquals(4.5, car.fuel)

    }
}