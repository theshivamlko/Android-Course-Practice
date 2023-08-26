package com.example.daggerexample.example2

import dagger.Module
import dagger.Provides


@Module
class Display2Module {

    @Provides
    fun getInstance(display2InterfaceUsage: Display2InterfaceUsage): Display2 {

        return display2InterfaceUsage
    }
}