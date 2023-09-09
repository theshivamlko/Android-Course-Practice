package com.example.sideeffects

sealed class UIEvents   {

    data class ShowMessage(val message: String):UIEvents()

}