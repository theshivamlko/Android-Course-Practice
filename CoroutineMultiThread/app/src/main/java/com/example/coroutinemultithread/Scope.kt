package com.example.coroutinemultithread

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


suspend fun main() {
    println("fun STATE")

    runBlocking {
        launch {
            delay(1000L)
            println("runBlocking")

        }

        GlobalScope.launch {
            delay(500L)
            println("GlobalScope launch")
        }
        coroutineScope {
            launch {
                delay(1500L)
                println("coroutineScope launch")
            }
        }
    }

    println("fun END")

}

