package com.example.databinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var binding:ActivityMainBinding
    private  lateinit var viewModel:MainActivityViewModel
    private  lateinit var myViewModelFactory: MyViewModelFactory
   // var count = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        myViewModelFactory= MyViewModelFactory(100)
        viewModel=ViewModelProvider(this,myViewModelFactory).get(MainActivityViewModel::class.java)

        println("ViewModel ${viewModel.count}")
        binding.let {
            it.textView2.setText("${viewModel.count}" )
        }
        binding.apply {
             textView.setText("Shivam")
        }

        binding.button.setOnClickListener {
            println("Button")

            startActivity(Intent(this,MainActivity2::class.java))
        }

        binding.button2.setOnClickListener {
            println("ViewModel")
        //    binding.textView2.setText("${++count}" )
             binding.textView2.setText("${++viewModel.count}")
        }

        binding.button3.setOnClickListener {
            println("ViewModel")
        startActivity(Intent(this,LiveDataActivity::class.java))
        }
    }

    override fun onStop() {
        super.onStop()
        println("onStop")
    }

    override fun onPause() {
        super.onPause()
        println("onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        println("onDetachedFromWindow")
    }


}

