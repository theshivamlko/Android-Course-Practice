package com.example.daggerexample.example2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.daggerexample.R
import javax.inject.Inject

class Example2Activity : AppCompatActivity() {


    lateinit var mobile2: Mobile2

    @Inject
    lateinit var mobile3: Mobile2

    lateinit var mobile4: Mobile2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example2)

        // Normal Injection
//        var mobileComponent: MobileComponent2 =
//            DaggerMobileComponent2.create()
//        mobile2 = mobileComponent.getMobile()
//        mobile2.call()


        println("Activity==========Injection")

        // Allow Injection thru Activity/Fragment only
//        var mobileComponent2 =
//            DaggerMobileComponent2.create()
//        mobileComponent2.inject(this)
//        mobile3.call()


        println("BuilderParam==========Injection")

        // Allow Injection with parameter
/*
        var mobileComponent3 =
            DaggerMobileComponent2.builder()
                .amoledDisplay3rdPartyClassModule(AmoledDisplay3rdPartyClassModule(200))
                .build()
        mobile4 = mobileComponent3.getMobile()
        mobile4.call()
*/

        var mobileComponent3 =
            DaggerMobileComponent2.builder()
                .amoledDisplay3rdPartyClassModule(AmoledDisplay3rdPartyClassModule(200))
                .build()
       val mobile5 = mobileComponent3.getMobile()
       val mobile6 = mobileComponent3.getMobile()
        println("Singleton check ${mobile5.hashCode()} ${mobile6.hashCode()}")




    }
}