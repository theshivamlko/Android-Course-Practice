package com.example.daggerexample



class Battery {
    // Do Not modify
    // Cannot @Inject here
    // Consider this code to Use/Read only
   //  and not accessible to change

    fun getPercentage():Int {
        println("getPercentage")
        return 90
    }
}