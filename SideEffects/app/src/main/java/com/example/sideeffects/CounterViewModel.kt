package com.example.sideeffects

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CounterViewModel:ViewModel() {

    val _screenState= mutableStateOf(MainScreenState(
        inputValue = "",
        displayResult = "Total is 0.0",
    ))

    val screenState:State<MainScreenState> =_screenState

    val _uiEventFlow= MutableSharedFlow<UIEvents>()
    val uiEventFlow= _uiEventFlow.asSharedFlow()

    var total=0.0

    fun addTotal(){
        total+=_screenState.value.inputValue.toDouble()
        _screenState.value=_screenState.value.copy(
            displayResult = "Total value is ${total}",
            isButtonShow = false
        )
    }

    fun reset(){
        total=0.0
        _screenState.value=_screenState.value.copy(
            displayResult = "Total value is $total",
            inputValue = "",
            isButtonShow = false

        )
    }


    fun onEvent(event: CounterEvents){

        when(event){
            is CounterEvents.ValueEntered->{
                _screenState.value=_screenState.value.copy(
                    inputValue = event.value,
                    isButtonShow = true
                )
            }
            is CounterEvents.CountButtonClicked->{
                addTotal()
                viewModelScope.launch {
                    _uiEventFlow.emit(
                        UIEvents.ShowMessage(message = "value added successfully")
                    )
                }
            }

            is CounterEvents.ResetButtonClicked ->{
                reset()
                viewModelScope.launch {
                    _uiEventFlow.emit(
                        UIEvents.ShowMessage(message = "value reset successfully")
                    )
                }
            }

            else -> {}
        }
    }
}