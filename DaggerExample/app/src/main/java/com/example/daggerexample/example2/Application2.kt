package com.example.daggerexample.example2

import android.app.Application
import javax.inject.Inject

class Application2:Application() {

    @Inject // Inject at singleton
    lateinit var mobile4:Mobile2

    lateinit var application2:Application2


    override fun onCreate() {
        super.onCreate()

        println("Application2 onCreate")

        application2=this

        var mobileComponent3 =
            DaggerMobileComponent2.builder()
                .amoledDisplay3rdPartyClassModule(AmoledDisplay3rdPartyClassModule(200))
                .build()
        mobile4 = mobileComponent3.getMobile()
        mobile4.call()

    }

}