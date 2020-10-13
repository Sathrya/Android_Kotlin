package com.example.navigation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.navigation.model.UsersInfo

@Database(entities = [UsersInfo::class],version = 1,exportSchema = false)
abstract class UserDatabase :RoomDatabase(){
    abstract fun UserDao():UserDao

    companion object{
        @Volatile
        private var INSTANCE:UserDatabase?=null

        fun getDatabase(context: Context):UserDatabase{
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(context.applicationContext,UserDatabase::class.java,"User-Database").build()
                INSTANCE=instance
                return instance
            }
        }
    }
}