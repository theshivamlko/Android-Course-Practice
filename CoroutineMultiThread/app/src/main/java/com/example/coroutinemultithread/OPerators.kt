package com.example.coroutinemultithread

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking


fun main() {

    runBlocking {
        mapOperator()
        reduces()
    }

}

suspend fun mapOperator() {
    (1..10).asFlow().map {
        "ABC $it"
    }.collect {
        println(it)
    }

}

suspend fun reduces() {
    (1..5).asFlow().reduce { a, b ->
        "$a$b".toInt()
    }

}