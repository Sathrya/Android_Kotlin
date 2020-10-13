package com.example.navigation.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UsersInfo (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var fname:String,
    var lname:String,
    var email:String,
    var gender:String,
    var status:String
)