package com.example.daggerexample.example1

import dagger.Module
import dagger.Provides


@Module
class BatteryModule {
// To access @Battery2 Class -> cannot be modified only use
// So created a Module to utilize Battery2 features
    @Provides
    fun getBattery(): Battery {
        return  Battery()
    }
}