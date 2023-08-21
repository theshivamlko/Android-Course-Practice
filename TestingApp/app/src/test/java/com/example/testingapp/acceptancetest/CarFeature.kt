package com.example.testingapp.acceptancetest

import com.example.testingapp.outsideintdd.Car
import com.example.testingapp.outsideintdd.Engine
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class CarFeature {

    private val engine= Engine(false,15)

    //Try till Failing Acceptance is Passed
    private val car: Car =Car(6.0,engine)




    @Test
    fun carIsLoosingFuelWhenItTurnsON(){
        car.turnOn()

         assertEquals(5.5,car.fuel)
    }


    @Test
    fun carIsTurningOnEngineAndIncreaseTheTemperature(){
        car.turnOn()

        assertTrue(car.engine.isTurnOn)
        assertEquals(95,car.engine.temperature)
    }
}