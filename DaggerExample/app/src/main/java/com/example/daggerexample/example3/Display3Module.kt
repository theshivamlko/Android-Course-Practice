package com.example.daggerexample.example2

import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
class Display3Module {

    @Provides
    fun getInstance(display2InterfaceUsage: Display3InterfaceUsage): Display2 {

        return display2InterfaceUsage
    }
}