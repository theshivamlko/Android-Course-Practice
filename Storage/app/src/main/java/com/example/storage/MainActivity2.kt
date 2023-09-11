package com.example.storage

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    private lateinit var mStore: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mStore = findViewById<Button>(R.id.store)

        mStore.setOnClickListener {
            val sharedPreferences = getSharedPreferences("Practice", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("name", "Shivam")
            editor.putStringSet("set", setOf("1", "2", "3", "4"))
            editor.apply()
            Handler(Looper.myLooper()!!).postDelayed(Runnable {
                println(sharedPreferences.getString("name", null))
                println(sharedPreferences.getStringSet("set", null))
            }, 3000)

        }


    }
}