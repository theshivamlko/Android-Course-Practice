package com.example.roomdbmvvm.repositories

import com.example.roomdbmvvm.roomdb.User
import com.example.roomdbmvvm.roomdb.UserDAO

class UserRepository(private val dao: UserDAO) {

    val users = dao.getAllUsersInDB()

    suspend fun insert(user: User): Long{
        return dao.insertUser(user)
    }

    suspend fun delete(user: User) {
        return dao.deleteUser(user)
    }
    suspend fun update(user: User) {
        return dao.updateUser(user)
    }


}
