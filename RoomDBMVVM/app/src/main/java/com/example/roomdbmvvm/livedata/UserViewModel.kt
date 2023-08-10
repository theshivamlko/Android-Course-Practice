package com.example.roomdbmvvm.livedata

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdbmvvm.repositories.UserRepository
import com.example.roomdbmvvm.roomdb.User
import kotlinx.coroutines.launch
import kotlin.random.Random

class UserViewModel(val repositories: UserRepository) : ViewModel(), Observable {

    val userList = repositories.users
    var isUpdateOrDelete = false
    lateinit var userToUpdateOrDelete: User

    @Bindable
    val nameEdt = MutableLiveData<String?>()

    @Bindable
    val emailEdt = MutableLiveData<String?>()

    @Bindable
    val btnAddUpdateTxt = MutableLiveData<String>()

    @Bindable
    val btnClearDeleteTxt = MutableLiveData<String>()

    init {
        btnAddUpdateTxt.value = "ADD"
        btnClearDeleteTxt.value = "CLEAR"
    }

    fun saveOrUpdate() {
        val name = nameEdt.value!!
        val email = emailEdt.value!!
        if (isUpdateOrDelete) {
            userToUpdateOrDelete = User(userToUpdateOrDelete.id, name, email)
            update(userToUpdateOrDelete)
        } else {
            insert(User(Random(10000).nextInt(), name, email))
        }


        nameEdt.value = null
        emailEdt.value = null
    }


    fun insert(user: User) = viewModelScope.launch {
        repositories.insert(user)
    }

/*    fun delete(user: User) = viewModelScope.launch {
        repositories.delete(user)
    }

    fun clearAll( ) = viewModelScope.launch {
        repositories.deleteAll( )
    }*/

    fun update(user: User) = viewModelScope.launch {
        repositories.update(user)

        // Reset Views
        nameEdt.value = null
        emailEdt.value = null
        isUpdateOrDelete=false
        btnAddUpdateTxt.value="SAVE"
        btnClearDeleteTxt.value = "CLEAR ALL"

    }

 /*   fun getUserById(id: Int) = viewModelScope.launch {
        repositories.getUserById(id)
    }
*/

    fun initUpdateAndDelete(user: User) {
        nameEdt.value = user.name
        emailEdt.value = user.email
        isUpdateOrDelete=true
        btnAddUpdateTxt.value = "UPDATE"
        btnClearDeleteTxt.value = "DELETE"
    }

    fun clearOrDelete() {

        if (isUpdateOrDelete) {

        }else{

        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {


    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}