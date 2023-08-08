package com.example.unitconvertorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DrawableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable)

        val btnIntent1: Button by lazy { findViewById<Button>(R.id.btnIntent1) }
        val btnIntent2: Button by lazy { findViewById<Button>(R.id.btnIntent2) }

        btnIntent1.setOnClickListener {

        }

    }
}