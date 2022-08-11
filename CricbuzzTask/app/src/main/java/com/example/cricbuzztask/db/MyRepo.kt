package com.example.cricbuzztask.db

import androidx.lifecycle.LiveData
import com.example.cricbuzztask.model.Menu.MenuItems
import com.example.cricbuzztask.model.Restaurant.ModelItem


class MyRepo (private val db: MyDatabase) {

    val readAllData:LiveData<List<ModelItem>> = db.myDao().readdata()

    val readMenuData:LiveData<List<MenuItems>> = db.myDao().readMenuData()

    suspend fun addData(modelItem: ModelItem){
        db.myDao().insertData(modelItem)
    }

    suspend fun addMenuData(menuItems: MenuItems){
        db.myDao().insertDataIntoMenu(menuItems)
    }

    fun searchData(query: String):LiveData<List<ModelItem>>{
        return db.myDao().searchData(query)
    }

    fun searchMenuData(query: String):LiveData<List<MenuItems>>{
        return db.myDao().searchMenuData(query)
    }


}