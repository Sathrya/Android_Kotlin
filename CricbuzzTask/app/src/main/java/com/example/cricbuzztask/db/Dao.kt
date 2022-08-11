package com.example.cricbuzztask.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cricbuzztask.model.Menu.MenuItems
import com.example.cricbuzztask.model.Restaurant.ModelItem

@Dao
interface Dao {

    @Insert(entity = ModelItem::class,onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(modelItem: ModelItem)

    @Insert(entity = MenuItems::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataIntoMenu(menuItems: MenuItems)

    @Query("Select * from restaurants")
    fun readdata(): LiveData<List<ModelItem>>

    @Query("Select * from menus ")
    fun readMenuData(): LiveData<List<MenuItems>>

    @Query("Select * from restaurants where name LIKE:query or cuisineType LIKE:query")
    fun searchData(query: String):LiveData<List<ModelItem>>

    @Query("Select * from menus where name LIKE:query")
    fun searchMenuData(query: String):LiveData<List<MenuItems>>
}