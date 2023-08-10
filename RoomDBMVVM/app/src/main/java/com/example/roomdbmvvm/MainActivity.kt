package com.example.roomdbmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdbmvvm.databinding.ActivityMainBinding
import com.example.roomdbmvvm.livedata.UserViewModel
import com.example.roomdbmvvm.livedata.UserViewModelFactory
import com.example.roomdbmvvm.repositories.UserRepository
import com.example.roomdbmvvm.roomdb.User
import com.example.roomdbmvvm.roomdb.UserDatabase

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var userViewModel: UserViewModel
      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val dao = UserDatabase.getInstance(this).userDao

         val userViewModelFactory = UserViewModelFactory(UserRepository(dao))

        userViewModel=ViewModelProvider(this,userViewModelFactory).get(UserViewModel::class.java)


        activityMainBinding.userViewModel=userViewModel

        activityMainBinding.lifecycleOwner=this


        activityMainBinding.apply {

            recyclerView.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)

            userViewModel?.userList?.observe(this@MainActivity, Observer {

                val recyclerAdapter: RecyclerAdapter = RecyclerAdapter(it) { selectedUser ->
                    onClick(selectedUser)
                }

                recyclerView.adapter=recyclerAdapter
            })
        }


    }

    private fun  onClick(selectedUser: User) {
        println("${selectedUser.id} ______ ${selectedUser.name} ______ ${selectedUser.email}")
        userViewModel.initUpdateAndDelete(selectedUser)
    }
}