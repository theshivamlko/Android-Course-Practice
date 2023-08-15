package com.example.coroutinemultithread

import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.runBlocking


fun main() {

    runBlocking {
        val tickerChannel= ticker(1000,0)
        tickerChannel.consumeEach {
            println(System.currentTimeMillis().toString())
        }
        tickerChannel.cancel()

    }




}

