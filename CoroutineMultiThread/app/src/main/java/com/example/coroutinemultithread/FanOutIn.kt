package com.example.coroutinemultithread

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {


    // fanOut()

    fanIn()

}

fun fanIn() {
    println("fanIn START")
    runBlocking {
        var channel = Channel<Int>()
        async {
            repeat(10) {
                delay(200L)
                sendProcess(it, channel)
            }
        }

        repeat(10) {
            println("fanIn $it receive  ${channel.receive()}")
        }
    }
    println("fanIn END")
}

fun fanOut() {
    runBlocking {
        println("runBlocking START")

        var channel = produce<Int> {
            for (i in 1..5) {
                println("send $i")
                channel.send(i)
                //  delay(200L)
            }
        }

        launchProcess(1, channel)
        launchProcess(2, channel)
        launchProcess(3, channel)
        launchProcess(4, channel)
        launchProcess(5, channel)
        launchProcess(6, channel)

        println("runBlocking END")
    }
}

fun CoroutineScope.launchProcess(id: Int, channel: ReceiveChannel<Int>) {
    launch {
        println("launchProcess ${id} ${channel.toList()}")
    }
}

fun CoroutineScope.sendProcess(id: Int, channel: SendChannel<Int>) {
    launch {
        println("sendProcess $id  ")
        channel.send(id)
    }
}
