package com.example.daggerexample.example2

import javax.inject.Inject


class Battery2 @Inject constructor()  {
    // Do Not modify
    // Cannot @Inject here
    // Consider this code to Use/Read only
   //  and not accessible to change

    init {
        println("init Battery2")

    }
    fun getPercentage():Int {
        println("getPercentage")
        return 90
    }
}