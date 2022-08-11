package com.example.cricbuzztask

import android.util.Log
import androidx.lifecycle.*
import com.example.cricbuzztask.db.MyRepo
import com.example.cricbuzztask.model.Menu.MenuItems
import com.example.cricbuzztask.model.Restaurant.ModelItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyViewModel (private val repo: MyRepo): ViewModel() {

    var readAll:LiveData<List<ModelItem>> = repo.readAllData

    var readAllMenu:LiveData<List<MenuItems>> = repo.readMenuData

    fun saveData(modelItem: ModelItem){
        viewModelScope.launch(Dispatchers.IO){
            repo.addData(modelItem)
        }
    }

    fun saveMenuData(menuItems: MenuItems){
        Log.d("Main Viewmodel",menuItems.toString())
        viewModelScope.launch(Dispatchers.IO){
            repo.addMenuData(menuItems)
        }
    }

    fun searchData(query: String): LiveData<List<ModelItem>> {
        return repo.searchData(query)
    }

    fun searchMenuData(query: String): LiveData<List<MenuItems>> {
        return repo.searchMenuData(query)
    }

}