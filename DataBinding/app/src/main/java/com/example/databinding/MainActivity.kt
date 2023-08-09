package com.example.databinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.let {
            it.textView
        }
        binding.apply {
             textView.setText("Shivam")
        }

        binding.button.setOnClickListener {
            println("Button")

            startActivity(Intent(this,MainActivity2::class.java))
        }
    }
}

