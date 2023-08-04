package com.example.myapplication

import java.util.Scanner


fun main() {
    var s1= Scanner(System.`in`)

    println(s1.next())

    var list= mutableListOf<Int>(1,2,3)
    var list2= list.toMutableList()
    println(list)
    list2.add(10)
    println(list)
    println(list2)

}
class Collections {
}