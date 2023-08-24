package com.example.androidarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.androidarchitecture.databinding.ActivityMainBinding
import com.example.androidarchitecture.mvc.MVCActivity
import com.example.androidarchitecture.mvp.MVPActivity
import com.example.androidarchitecture.mvvm.MVVMActivity

class MainActivity : AppCompatActivity() {


    private lateinit var activityMainBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  setContentView(R.layout.activity_main)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        activityMainBinding.button.setOnClickListener {
            MVCActivity.startMVC(this)
        }
        activityMainBinding.button2.setOnClickListener {
            MVPActivity.startMVP(this)
        }
        activityMainBinding.button3.setOnClickListener {
            MVVMActivity.startMVVM(this)
        }


    }


    fun onMVC(view: View) {

        MVCActivity.startMVC(applicationContext)

    }

    fun onMVP(view: View) {

        MVCActivity.startMVC(applicationContext)

    }

    fun onMVVM(view: View) {

        MVCActivity.startMVC(applicationContext)

    }
}