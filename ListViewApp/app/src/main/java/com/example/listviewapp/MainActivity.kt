package com.example.listviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var mButton: Button
    lateinit var mButton2: Button
    lateinit var mButton3: Button
    lateinit var mButton4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        mButton = findViewById<Button>(R.id.button)
        mButton2 = findViewById<Button>(R.id.button2)
        mButton3 = findViewById<Button>(R.id.button3)
        mButton4 = findViewById<Button>(R.id.button4)

        mButton.setOnClickListener { l->

            startActivity(Intent(this,ListViewActivity1::class.java))
        }


        mButton2.setOnClickListener { l->

            startActivity(Intent(this,CustomListActivity::class.java))
        }
        mButton3.setOnClickListener { l->

            startActivity(Intent(this,RecycleViewActivity::class.java))
        }

    }
}