package com.example.daggerexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.daggerexample.databinding.ActivityMainBinding
import com.example.daggerexample.example1.Mobile
import com.example.daggerexample.example1.MobileApplication
import com.example.daggerexample.example2.Example2Activity
import javax.inject.Inject


// Dependency Injection
class MainActivity : AppCompatActivity() {


    lateinit var activityMainBinding: ActivityMainBinding

    //    @Inject  // Field Injection
    //   lateinit var mobile: Mobile2

    // private lateinit var mobile: Mobile2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        activityMainBinding.button2.setOnClickListener {
            startActivity(Intent(this,Example2Activity::class.java))
        }

        /*  mobile = DaggerMobileComponent.create().getMobileInstance()
          mobile.call()*/


        // Auto initialize variable
        /*    DaggerMobileComponent.create().inject(this)
           mobile.call()*/


        // Auto initialize variable
        /*       DaggerMobileComponent.builder().amoledDisplayModule(AmoledDisplayModule(1920))
                   .build()
                   .inject(this)
               mobile.call()*/


        // Using Application class
        /* (application as MobileApplication).mobile.inject(this)
         mobile.call()*/

    }
}