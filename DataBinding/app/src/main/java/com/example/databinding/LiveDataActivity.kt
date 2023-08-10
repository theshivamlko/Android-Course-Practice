package com.example.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.databinding.databinding.ActivityLivedataBinding
import com.example.databinding.databinding.ActivityMainBinding

class LiveDataActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityLivedataBinding
    private  lateinit var viewModel: Activity3ViewModel
    private  lateinit var factory: Activity3ViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_livedata)

        factory= Activity3ViewModelFactory(10)

        viewModel=ViewModelProvider(this,factory).get(Activity3ViewModel::class.java)

        binding.apply {
            viewModel.totalSum.observe(this@LiveDataActivity,{
                // executes on data change and on configuration change to update UI
                println("totalSum observe read ${it}")

            })

            button4.setOnClickListener {
              val num=  binding.editTextText.text.toString().toInt()
                println("button4 ${num}")
                viewModel.setTotal(num)

            }

        }


        // Observing any data change




    }
}