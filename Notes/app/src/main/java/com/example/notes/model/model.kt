package com.example.notes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes_Table")
data class model(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title:String,
    var note:String
)
