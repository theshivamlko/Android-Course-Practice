package com.example.myapplication

fun main() {

    var car=Car();

    car.startEngine()
    car.accelerate()
}

class Car:Vehicle{
    override val speed: Int
        get() = speed

    override fun startEngine(): String {

        println("override startEngine")
        return  ""
    }

}
interface  Vehicle{
    val speed:Int
    fun startEngine():String
    fun accelerate(){
        println("accelerate")
    }


}