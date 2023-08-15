package com.example.coroutinemultithread

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {

    runBlocking {
        println("main pipeline start")
        val v1 = channel()
        val v2 = channelClose()
        val v3 = channelProducer()
        println("main pipeline end $v1 \n $v2 \n $v3")
    }


}


fun channelProducer():String {
    var str=""
   return runBlocking {
        val channel = produce<Int> {// runs async
            for (c in 1..5) {
                //  delay(100L)
                println("produce send $c")
                channel.send(c)
            }
        }

        for (c in channel) {
            delay(500L)
            str+="$c"
            println("produce receive $c")

        }

        return@runBlocking str
    }
}


fun channelClose() {

    runBlocking {
        println("channelClose START")
        val channel = Channel<Int>()

        launch {
            for (c in 1..5) {
                delay(100L)
                println("channelClose send $c")
                channel.send(c)
            }
            channel.close()
        }
        for (c in channel) {

            delay(500L)
            println("channelClose receive $c")

        }
        println("channelClose END")

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
