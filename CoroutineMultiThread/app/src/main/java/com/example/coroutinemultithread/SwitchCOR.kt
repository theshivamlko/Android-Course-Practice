package com.example.coroutinemultithread

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


fun main() {

    runBlocking {
        launch(Dispatchers.IO) {
            println("IO ${coroutineContext}")

            withContext(coroutineContext){
                println("withContext ${coroutineContext}")

            }
            println("IO2 ${coroutineContext}")

        }
    }

}