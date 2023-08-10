package com.example.roomdbmvvm.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 2, entities = [User::class ] )
abstract class UserDatabase:RoomDatabase() {

    abstract val userDao:UserDAO

    // Singleton Design Pattern
    companion object {
        @Volatile
        private var INSTANCE:UserDatabase?=null
        fun getInstance(context:Context):UserDatabase {
            synchronized(this){
                var instance  = INSTANCE

                if(instance==null){
                    instance= Room.databaseBuilder(context.applicationContext,UserDatabase::class.java,"mydb").build()
                }
                INSTANCE = instance

                return instance
            }




        }
    }

}