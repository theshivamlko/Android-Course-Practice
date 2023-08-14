package com.example.coroutinemultithread

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var counter=0
fun main() {

    GlobalScope.launch{
        message()
    }

    GlobalScope.launch{
        message2()
    }
    println("Hello")

    Thread.sleep(2000L)

    println("END $counter")


}


suspend fun message(){
    delay(500L)
    println("message")
    counter++
}

suspend fun message2(){
    delay(1000L)
    println("message2")
    counter++

}