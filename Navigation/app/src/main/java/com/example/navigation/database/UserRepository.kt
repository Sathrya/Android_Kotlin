package com.example.navigation.database

import androidx.lifecycle.LiveData
import com.example.navigation.model.UsersInfo

class UserRepository(private val userDao:UserDao) {
    val readAllData:LiveData<List<UsersInfo>> = userDao.readAllData()

    suspend fun addUser(user: UsersInfo){
        userDao.registerUser(user)
    }

    suspend fun deleteUser(user:UsersInfo){
        userDao.deleteUser(user)
    }

    suspend fun updateUSer(user: UsersInfo){
        userDao.updateUser(user)
    }
}