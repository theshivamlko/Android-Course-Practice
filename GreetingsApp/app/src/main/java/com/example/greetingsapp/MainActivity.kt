package com.example.greetingsapp

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : Activity() {


    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)

        setContentView(R.layout.main_activity)

        val nameEdt = findViewById<EditText>(R.id.name)
        val loginBtn: Button = findViewById(R.id.loginBtn)

        loginBtn.setOnClickListener {

            val s=nameEdt.text.toString()
            Toast.makeText(this,"Hello $s",Toast.LENGTH_SHORT).show()
        }

    }

}