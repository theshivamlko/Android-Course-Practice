package com.example.daggerexample

import dagger.Module
import dagger.Provides


@Module
class BatteryModule {
// To access @Battery Class -> cannot be modified only use
// So created a Module to utilize Battery features
    @Provides
    fun getBattery():Battery{
        return  Battery()
    }
}