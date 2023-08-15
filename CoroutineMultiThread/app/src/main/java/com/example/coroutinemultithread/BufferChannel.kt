package com.example.coroutinemultithread

import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


fun main() {
    buffer()
}

fun buffer() {
    println("buffer START")
    runBlocking {
        var channel = Channel<Int>(2)
        async {
            repeat(5) {
                delay(1000L)
                sendProcess(it, channel)
            }
        }
        delay(1000L)

        repeat(5) {
            delay(1000L)
            println("buffer  Receive  ${channel.receive()}")
        }
    }
    println("buffer END")
}