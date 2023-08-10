package com.example.roomdbmvvm.livedata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdbmvvm.repositories.UserRepository

class UserViewModelFactory(private val repositories: UserRepository):ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            return  UserViewModel(repositories) as T
        }

        throw  IllegalArgumentException("Unknown ViewModel")

    }


}