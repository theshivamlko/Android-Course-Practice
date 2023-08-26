package com.example.daggerexample.example2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.daggerexample.R

class Example2Activity : AppCompatActivity() {


    lateinit var mobile2: Mobile2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example2)


       var mobileComponent:MobileComponent2=
           DaggerMobileComponent2.create()

        mobile2=mobileComponent.getMobile()
        mobile2.call()

    }
}