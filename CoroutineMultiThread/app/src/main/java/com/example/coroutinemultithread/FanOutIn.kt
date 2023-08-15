package com.example.coroutinemultithread

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {


    fanOut()


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
            println("launchProcess ${id } ${channel.toList()}")

    }
}
