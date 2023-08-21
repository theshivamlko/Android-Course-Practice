package com.example.testingapp.acceptancetest

import com.example.testingapp.outsideintdd.Car
import junit.framework.TestCase.assertEquals
import org.junit.Assert
 import org.junit.Test

class CarFeature {

    //Try till Failing Acceptance is Passed
    val car: Car =Car(6.0)
    @Test
    fun carIsLoosingFuelWhenItTurnsON(){
        car.turnOn()

         assertEquals(5.5,car.fuel)
    }
}