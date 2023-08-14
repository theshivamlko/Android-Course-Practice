package com.example.coroutinemultithread

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {

    runBlocking {
        launch(CoroutineName("MyCOR1")) {
            delay(1000L)
            println("runBlocking ${this.coroutineContext.get(CoroutineName.Key)}")
        }
    }
}