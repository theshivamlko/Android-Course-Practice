package com.example.daggerexample

import dagger.Component


@Component(modules = [BatteryModule::class,AmoledDisplayModule::class])
interface MobileComponent {
   fun getMobileInstance():Mobile // Dependent as return type
   // If use MobileComponent in different Classes
   fun inject(mainActivity: MainActivity)
}