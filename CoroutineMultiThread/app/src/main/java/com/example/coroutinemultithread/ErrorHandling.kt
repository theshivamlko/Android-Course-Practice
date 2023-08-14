package com.example.coroutinemultithread

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {

    runBlocking {
        val errorHandler = CoroutineExceptionHandler { cor, thro ->
            println("errorHandler Exception ${thro.stackTrace}")
        }

        val job = GlobalScope.launch(errorHandler) {
            println("Throw Exception")
            throw IllegalAccessException("ABC")
            println("END")
        }
        job.join()

        val asyntask = GlobalScope.async {
            println("Throw ASYNC Exception")
            throw UnsupportedOperationException("ABC")
            println("ASYNC END")
        }

        try {
            asyntask.await()

        } catch (e: Exception) {
            println("errorHandler ASYNC Exception ${e.stackTrace}")

        }

    }

}