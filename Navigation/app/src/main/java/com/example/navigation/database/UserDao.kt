package com.example.navigation.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.navigation.model.UsersInfo

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun registerUser(user: UsersInfo)

    @Query("SELECT * FROM users ORDER BY id ASC")
    fun readAllData(): LiveData<List<UsersInfo>>

    @Delete
    suspend fun deleteUser(user:UsersInfo)

    @Update
    suspend fun updateUser(user:UsersInfo)
}