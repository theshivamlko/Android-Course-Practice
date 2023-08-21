package com.example.testingapp.unittest

 import com.example.testingapp.outsideintdd.Engine
import junit.framework.TestCase.*
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class EngineShould {

    private val engine = Engine(false,15)



    @Test
    fun isEngineTurnOn() {
        engine.turnOn()

        assertTrue(engine.isTurnOn)
    }

    @Test
    fun riseTheTempWhenEngineIsOn() {

        engine.turnOn()
        assertEquals(95, engine.temperature)
    }
    @Test
    fun turnOnItsEngineViaSuspend() {

        runBlocking {
            engine.turnOnViaSuspend()
            assertEquals(90, engine.temperature)
        }

    }


}