package com.example.coroutinemultithread

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

fun main() {

    runBlocking {
        println("sharedState1 START")
        withContext(Dispatchers.Default){
            sharedState1{
                counter++
            }
        }
        println("sharedState1 END $counter")
    }
}



suspend fun sharedState1(action:suspend()->Unit) {
// run n*k times
    val n=100
    val k=1000
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