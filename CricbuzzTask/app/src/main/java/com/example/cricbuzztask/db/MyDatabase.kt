package com.example.cricbuzztask.db

import android.content.Context
import androidx.room.*
import com.example.cricbuzztask.Converters
import com.example.cricbuzztask.model.Menu.MenuItems
import com.example.cricbuzztask.model.Restaurant.ModelItem

@Database(entities = [ModelItem::class, MenuItems::class],version = 2,exportSchema = false)

abstract class MyDatabase: RoomDatabase() {
    abstract fun myDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null
        fun getDatabase(context: Context): MyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "Restaurant-DB"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}