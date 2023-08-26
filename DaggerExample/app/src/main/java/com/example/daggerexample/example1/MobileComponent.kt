package com.example.daggerexample.example1

import com.example.daggerexample.MainActivity
import dagger.Component


@Component(modules = [BatteryModule::class, AmoledDisplayModule::class])
interface MobileComponent {
   fun getMobileInstance(): Mobile // Dependent as return type
   // If use MobileComponent2 in different Classes
   fun inject(mainActivity: MainActivity)
}