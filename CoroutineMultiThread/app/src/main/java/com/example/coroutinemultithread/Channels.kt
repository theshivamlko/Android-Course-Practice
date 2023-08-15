package com.example.coroutinemultithread

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
  //  channel()
  //  channelClose()
    channelProducer()
}


  fun channelProducer() {
      runBlocking {
          val channel = produce<Int> {// runs async
              for (c in 1..5) {
                //  delay(100L)
                  println("produce send $c")
                  channel.send(c)
              }
          }

          for (c in channel) {
              delay(500L)
              println("produce receive $c")

          }
      }
  }


  fun channelClose() {

    runBlocking {
        println("channel START")
        val channel = Channel<Int>()

        launch {
            for (c in 1..5) {
                delay(100L)
                println("channel send $c")
                channel.send(c)
            }
            channel.close( )
        }
        for (c in channel) {

            delay(500L)
            println("channel receive $c")

        }
        println("channel END")

    }

}
  fun channel() {

    runBlocking {
        println("channel START")
        val channel = Channel<Int>()

        launch {
            for (c in 1..5) {
                delay(100L)
                println("channel send $c")
                channel.send(c)
            }
        }
        for (c in 1..5) {

            delay(500L)
            println("channel receive $c")
            channel.receive()

        }
        println("channel END")

    }

}
