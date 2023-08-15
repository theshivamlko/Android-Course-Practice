package com.example.coroutinemultithread

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import kotlinx.coroutines.withTimeoutOrNull


suspend fun main() {


    println("Start")
    val obj = getValue()
    obj.collect {
        println(it)
    }

    kotlinx.coroutines.delay(200L)

    // will  END current Coroutine
    withTimeoutOrNull(500L) {
        obj.collect {
            println("  withTimeoutOrNull $it")
        }
        println("END withTimeoutOrNull")
    }
    println("END")
}

fun getValue(): Flow<Int> = flow {

    for (i in 1..10) {
        kotlinx.coroutines.delay(100L)
        emit(i)
    }

}