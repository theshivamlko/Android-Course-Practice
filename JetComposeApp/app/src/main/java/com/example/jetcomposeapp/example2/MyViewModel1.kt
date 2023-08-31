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
        mutableStateFlow.update {
           Thread.sleep(2000)
            120
        }
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

    fun updateSharedFlow() {
        viewModelScope.launch {
            delay(2000L)
            mutableSharedFlow.emit(kotlin.random.Random(0).nextInt(100))
        }
    }

}