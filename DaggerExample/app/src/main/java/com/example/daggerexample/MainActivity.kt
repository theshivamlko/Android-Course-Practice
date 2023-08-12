package com.example.daggerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.internal.DaggerGenerated


// Dependency Injection
class MainActivity : AppCompatActivity() {

    private lateinit var mobile: Mobile
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        DaggerMobileComponent.create().getMobileInstance().call()







    }
}