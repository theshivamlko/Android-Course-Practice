package com.example.coroutinemultithread

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {

    runBlocking {
      val job1=  launch {
          delay(3000L)
            println("JOB1")

          val job2=  launch {
              println("JOB2")
          }

          job2.invokeOnCompletion {
              println("JOB2 COMPLETE")

          }

        }

        job1.invokeOnCompletion {
            println("JOB1 COMPLETE")
        }

        job1.cancel()


    }
}

