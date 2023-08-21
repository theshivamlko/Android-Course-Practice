package com.example.testingapp.acceptancetest

import junit.framework.Assert.assertEquals
import org.junit.Assert
 import org.junit.Test

class CarFeature {

    val car:Car=Car(6.0)
    @Test
    fun carIsLoosingFuelWhenItTurnsON(){
        car.turnOn()

         assertEquals(5.5,car.fuel)
    }
}