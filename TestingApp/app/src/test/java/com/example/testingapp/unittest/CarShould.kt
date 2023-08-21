package com.example.testingapp.unittest

 import com.example.testingapp.outsideintdd.Car
import com.example.testingapp.outsideintdd.Engine
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class CarShould {

    // will not use constructor for instance use mockito
    private val engine: Engine = mock(Engine::class.java)
    private val car = Car(5.0,engine)


    @Test
    fun loosingFuelWhenItTurnsOn() {

        car.turnOn()

        assertEquals(4.5, car.fuel)

    }


    @Test
    fun turnOnItsEngine(){
        // 1. call function
        car.turnOn()
        // 2. verify the mock engine calls 1 times turnOn() from  car.turnOn()
       verify(engine,times(1)).turnOn()

    }
}