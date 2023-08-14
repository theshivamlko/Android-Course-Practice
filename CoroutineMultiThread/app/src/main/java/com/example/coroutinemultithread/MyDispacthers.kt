package com.example.coroutinemultithread

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking


fun main() {
    runBlocking {
//        launch(Dispatchers.Main) {
//            println("MAIN ${Thread.currentThread().name}")
//        }  // Only for Android

        launch(Dispatchers.Unconfined) {
            println("Unconfined1 ${Thread.currentThread().name}")
            delay(100L)
            println("Unconfined2 ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) {
            delay(1000L)
            println("Default ${Thread.currentThread().name}")
        }

        launch(Dispatchers.IO) {
            println("IO ${Thread.currentThread().name}")

            launch(Dispatchers.Unconfined) {
                println("Unconfined3 ${Thread.currentThread().name}")
                delay(100L)
                println("Unconfined4 ${Thread.currentThread().name}")
            }
        }

        launch(newSingleThreadContext("MyThread")) {
            println("newSingleThreadContext ${Thread.currentThread().name}")
        }


    }
}