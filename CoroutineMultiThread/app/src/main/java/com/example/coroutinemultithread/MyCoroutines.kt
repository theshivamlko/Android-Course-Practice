package com.example.coroutinemultithread

import kotlinx.coroutines.*


fun main(){
 // init()


}



fun init(){
    println("GlobalScope")
    GlobalScope.launch {
        delay(1000)
        println("GlobalScope launch")
    }

    println("GlobalScope Thread")

    // as program exit and doesn't wait for Thread

    runBlocking {
        repeat(100000000){
            launch {
                println("runBlocking  ")

            }
        }
    }
}



