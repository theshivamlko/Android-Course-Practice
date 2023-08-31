package com.example.daggerexample.example2

import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
abstract class Display3ModuleWithBind {
    @Binds
    abstract fun bindInstance(display2InterfaceUsage: Display3InterfaceUsage):Display2
}