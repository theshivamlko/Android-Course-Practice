package com.example.sideeffects


// sealed class Follow strict hierarchy
sealed class CounterEvents {

    data class ValueEntered(val value:String):CounterEvents()
    object CountButtonClicked :CounterEvents()
    object ResetButtonClicked :CounterEvents()


}