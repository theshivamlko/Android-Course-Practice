package com.example.jetcomposeapp.example2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MyViewModel1:ViewModel() {

    var editText:MutableLiveData<String> =MutableLiveData<String>()

    init {
        editText.postValue("Shivam")
    }

    fun getDataFlow():Flow<String>{
        println("MyViewModel1 getDataFlow")
        return  flow {
            delay(4000L)
            emit("Some flow data")
        }
    }

}