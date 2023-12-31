package com.example.myapplication.kotlin

fun main() {

    val person: Person = Person("ShivamPerson", 21);
    person.eating()
    println("------")

    val person2: Person = Person("ShivamPerson2", 21,bool = false);
    person2.eating()
    println("------")


    val player: Player = Player("ShivamPlayer", 23);
    player.play()
    player.eating()
    println("------")

    val player2: Player2 = Player2("ShivamPlayer", 23);
    player2.play()
    player2.eating()
    println("------")

    val player3: Player2 = Player2("ShivamPlayer", 23,false);
    player3.play()
    player3.eating()
    println("------")


    val player5: Player5 = Player5("ShivamPlayer5", 23,false);
    player5.play()
    player5.eating()
    println("------")


}

open class Person(val name: String, val age: Int) {
    init {
        println("Person init $name")
    }
    constructor(name2: String,age:Int,bool:Boolean=true):this(name2,age){
        println("Person Secondary constructor $name")
    }

    fun eating() {
        println("$name is eating")
    }
}


class Player(private val name2: String, private val age2: Int) : Person(name2, age2) {
    init {
        println("Player init")
    }
    fun play() {

        println("$name is playing")
    }

}
class Player2(private val name2: String, private val age2: Int) : Person(name2, age2,true) {
    init {
        println("Player2 init")
    }
    constructor(name3: String,age3:Int,bool:Boolean=true):this(name3,age3){
        println("Player2 Secondary constructor")
    }

    fun play() {
        println("$name is playing")
    }

}

class Player5 : Person {
    init {
        println("Player2 init")
    }
    constructor(name3: String,age3:Int,bool:Boolean=true):super(name3,age3){
        println("Player2 Secondary constructor")
    }

    fun play() {
        println("$name is playing")
    }

}

class Teacher(val name: String) {
    fun teach() {
        println("$name is teaching")
    }
}

