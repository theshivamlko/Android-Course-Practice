package com.example.daggerexample.example2

import dagger.Binds
import dagger.Module


@Module
abstract class Display2ModuleWithBind {
    @Binds
    abstract fun bindInstance(display2InterfaceUsage: Display2InterfaceUsage):Display2
}