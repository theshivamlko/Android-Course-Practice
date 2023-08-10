package com.example.livedataexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(var user: User):ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return  MainViewModel(user) as T
        }

        throw IllegalArgumentException("Unknown View Model")
    }
}