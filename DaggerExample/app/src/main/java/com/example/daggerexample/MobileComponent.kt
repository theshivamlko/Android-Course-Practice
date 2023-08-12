package com.example.daggerexample

import dagger.Component


@Component(modules = [BatteryModule::class])
interface MobileComponent {
   fun getMobileInstance():Mobile // Dependent as return type


}