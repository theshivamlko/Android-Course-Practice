package com.example.daggerexample.example2

import android.app.Application
import javax.inject.Inject

class Application3:Application() {

    @Inject // Inject at singleton
    lateinit var mobile4:Mobile3

    lateinit var application2:Application3


    override fun onCreate() {
        super.onCreate()

        println("Application2 onCreate")

        application2=this

        var mobileComponent3 =
            DaggerMobileComponent3.builder()
                .amoledDisplay3rdPartyClassModule3(AmoledDisplay3rdPartyClassModule3(200))
                .build()
      //  mobile4 = mobileComponent3.getMobile()
     //   mobile4.call()

    }

}