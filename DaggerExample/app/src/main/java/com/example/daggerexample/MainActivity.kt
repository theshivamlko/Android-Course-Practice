package com.example.daggerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.internal.DaggerGenerated
import javax.inject.Inject


// Dependency Injection
class MainActivity : AppCompatActivity() {


      @Inject  // Field Injection
      lateinit var mobile: Mobile

   // private lateinit var mobile: Mobile
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




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
       (application as MobileApplication).mobile.inject(this)
       mobile.call()

    }
}