package com.example.navigationcompose

sealed class DestinationUtil(val route:String) {

    object FirstScreen:DestinationUtil("First Screen")
    object SecondScreen:DestinationUtil("Second Screen")
}
