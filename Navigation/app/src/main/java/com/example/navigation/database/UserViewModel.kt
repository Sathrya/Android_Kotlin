package com.example.navigation.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.navigation.model.UsersInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {
    val readAllData:LiveData<List<UsersInfo>>
    private val userRepo:UserRepository
    init{
        val userDao = UserDatabase.getDatabase(application).UserDao()
        userRepo= UserRepository(userDao)
        readAllData=userRepo.readAllData
    }

    fun addUser(user: UsersInfo){
        viewModelScope.launch(Dispatchers.IO){
            userRepo.addUser(user)
        }
    }

    fun deleteUser(user: UsersInfo){
        viewModelScope.launch (Dispatchers.IO){
            userRepo.deleteUser(user)
        }
    }

    fun updateUser(user:UsersInfo){
        viewModelScope.launch (Dispatchers.IO){
            userRepo.updateUSer(user)
        }
    }
}