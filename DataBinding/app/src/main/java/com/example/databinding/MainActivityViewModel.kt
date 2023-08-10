package com.example.databinding

import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingNum: Int) : ViewModel() {
    var count = 0;
    init {
        count=startingNum
    }
}