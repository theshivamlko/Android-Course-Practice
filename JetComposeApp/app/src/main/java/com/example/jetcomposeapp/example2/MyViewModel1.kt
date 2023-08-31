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

    val count=0
    val myflow=flow {
        delay(2000L)
         emit(count)
    }
    fun getDataFlow():Flow<Int>{
        println("MyViewModel1 getDataFlow")
        return  myflow
    }

    fun update(){

    }

}