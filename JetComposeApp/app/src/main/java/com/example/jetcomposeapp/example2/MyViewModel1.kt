package com.example.jetcomposeapp.example2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Random
import java.util.logging.Handler

class MyViewModel1 : ViewModel() {

    var editText: MutableLiveData<String> = MutableLiveData<String>()

    // State Flow
    val mutableStateFlow = MutableStateFlow<Int>(100)
    var stateFlow: StateFlow<Int> = mutableStateFlow

    // Shared Flow
    val mutableSharedFlow = MutableSharedFlow<Int>(500)
    var sharedFlow: SharedFlow<Int> = mutableSharedFlow

    init {
        editText.postValue("Shivam")

    }

    val count = 0
    val myflow = flow {
        delay(2000L)
        emit(count)
    }

    fun getDataFlow(): Flow<Int> {
        println("MyViewModel1 getDataFlow")
        return myflow
    }

    var counter1=0
    var counter2=0

    fun updateSharedFlow() {
        viewModelScope.launch {
            repeat(10) {
                delay(500L)
                mutableSharedFlow.emit(++counter1)
            }
        }
    }
    fun updateStateFlow() {
        viewModelScope.launch {
            repeat(10) {
                delay(500L)
                mutableStateFlow.emit(++counter2)
            }
        }
    }

}