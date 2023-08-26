package com.example.daggerexample.example1

import android.app.Application

class MobileApplication:Application() {
lateinit var mobile: MobileComponent

    override fun onCreate() {
        mobile=initDagger()
        super.onCreate()

    }

  fun  initDagger(): MobileComponent =
      // Auto initialize variable
      DaggerMobileComponent.builder()
          .amoledDisplayModule(AmoledDisplayModule(1920))
          .build()

}