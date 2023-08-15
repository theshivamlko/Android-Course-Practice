package com.example.coroutinemultithread

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

fun main() {

  //  var counter=0 // OLD
    //Solution
    var counter=AtomicInteger(0)

    runBlocking {
        println("sharedState1 START")
        withContext(Dispatchers.Default){
            sharedState1{
                counter.incrementAndGet()
            }
        }
        println("sharedState1 END $counter")
    }
}


// Atomic Variable Solution
suspend fun sharedState1(action:suspend()->Unit) {
// run n*k times
    val n=100
    val k=10000
    val time= measureTimeMillis {
        coroutineScope {
            repeat(n){
                launch {
                    repeat(k){
                        action()
                    }
                }
            }
        }
    }

    println("sharedState1 Completed ${n*k} times in $time ms")
}

// Fine Grained Solution
suspend fun sharedState2(action:suspend()->Unit) {
// run n*k times
    val n=100
    val k=10000
    val time= measureTimeMillis {
        coroutineScope {
            repeat(n){
                launch {
                    repeat(k){
                        action()
                    }
                }
            }
        }
    }

    println("sharedState1 Completed ${n*k} times in $time ms")
}