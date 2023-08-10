package com.example.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Activity3ViewModel(startNum:Int):ViewModel() {
    var total=MutableLiveData<Int>()  // notify data change

    val totalSum:LiveData<Int>
        get() {
           return total  // return LiveData type
        }

    init {
        total.value=startNum
    }

    fun setTotal(input:Int ){
        total.value= total.value?.plus(input)
    }

}