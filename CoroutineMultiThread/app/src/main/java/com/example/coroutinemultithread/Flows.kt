package com.example.coroutinemultithread

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.time.delay
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.system.measureTimeMillis


suspend fun main() {


    /* println("Start")
     val obj = getValue()
     obj.collect {
         kotlinx.coroutines.delay(200L)
         println(it)
     }

     kotlinx.coroutines.delay(200L)

     // will  END current Coroutine
     withTimeoutOrNull(500L) {
         obj.collect {
             println("  withTimeoutOrNull $it")
         }
         println("END withTimeoutOrNull")
     }*/

    runBlocking {
        for(a in 1..10000000){
        println("runBlocking $a")

    }
    }

  val res=  flow<Int> {
        for(a in 1..10000000){
            println("flow $a")

        }
    }.collect()

    var lon = measureTimeMillis {
      getBuffer().buffer().collect {
            println("  getBuffer buffer $it")
            kotlinx.coroutines.delay(1000L)
        }
        val job=  getBuffer();

    }

    println("END $lon")
}

fun getValue(): Flow<Int> = flow {

    for (i in 1..10) {
        kotlinx.coroutines.delay(100L)
        emit(i)
    }

}

fun getBuffer(): Flow<Int> = flow {
    for (i in 1..10) {
        kotlinx.coroutines.delay(100L)
        println("getBuffer= $i")
        emit(i)
    }
}