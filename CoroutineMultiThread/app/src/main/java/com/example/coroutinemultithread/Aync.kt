package com.example.coroutinemultithread

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.locks.ReentrantLock


fun main() {

    runBlocking {

        //without async are in sequence
        val a = async { getValue1() }
        val b = async { getValue2() }
        delay(500L)
        println("END")

        println("Result ${a.await() + b.await()}")





    }
}

suspend fun getValue1(): Int {
    delay(2000L)
    println("getValue1 100")
    return 100
}

suspend fun getValue2(): Int {
    delay(1000L)
    println("getValue2 200")
    return 200
}