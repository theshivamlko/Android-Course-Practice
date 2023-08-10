package com.example.databinding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Activity3ViewModelFactory(val startNum:Int):ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(Activity3ViewModel::class.java)){
            return Activity3ViewModel(startNum) as T
        }
        throw IllegalArgumentException("Unknown View Model")
    }




}