package com.example.livedataexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.livedataexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var mainViewModelFactory: MainViewModelFactory
    lateinit var activityMainBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModelFactory = MainViewModelFactory(User("Shivam", 10))

        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        activityMainBinding.apply {
            button.setOnClickListener {
                mainViewModel.updateUser(User(editTextText1.text.toString(), editTextText2.text.toString().toInt()))
            }
        }


        mainViewModel.user.observe(this, {
            println("User Listen ${it.name}")
            activityMainBinding.textView.text = it.name
        })

        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            mainViewModel.updateUser(User("AAAAA", 100))

        },5000)


    }
}