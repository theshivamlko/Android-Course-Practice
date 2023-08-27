package com.example.jetcomposeapp.example2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel1:ViewModel() {

    var editText:MutableLiveData<String> =MutableLiveData<String>()

    init {
        editText.postValue("Shivam")
    }

}