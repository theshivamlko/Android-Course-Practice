package com.example.livedataexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(initUser:User):ViewModel() {
    var user=MutableLiveData<User>()
    init {
        user.value=initUser
    }
    fun updateAttendance(   attendance:Int){
        user.value?.attendance=attendance
    }
    fun updateUser(  user: User ){
        this.user.value=user
    }
}

data class User(val name:String,var attendance:Int)