package com.example.daggerexample.example2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.daggerexample.R
import javax.inject.Inject

class Example3Activity : AppCompatActivity() {


    lateinit var mobile2: Mobile3

    @Inject
    lateinit var mobile3: Mobile3

    @Inject
//    @field:[Named("amoled")]
    lateinit var mobile4: Mobile3

    lateinit var mobile5: Mobile3

    lateinit var mobile6: Mobile3


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

        var mobileComponent4 =   DaggerMobileComponent3.builder()
                .amoledDisplay3rdPartyClassModule3(AmoledDisplay3rdPartyClassModule3(200))
                .build()
        mobile4 = mobileComponent4.getMobile()
        println("Injection with parameter ==========$mobile4")
        mobile4.call()


        // Singleton
   /*     var mobileComponent3 =
            DaggerMobileComponent2.builder()
                .amoledDisplay3rdPartyClassModule(AmoledDisplay3rdPartyClassModule(200))
                .build()
       val mobile5 = mobileComponent3.getMobile()
       val mobile6 = mobileComponent3.getMobile()
        println("Singleton check ${mobile5.hashCode()} ${mobile6.hashCode()}")*/




    }
}