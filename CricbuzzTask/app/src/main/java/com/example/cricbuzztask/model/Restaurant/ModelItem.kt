package com.example.cricbuzztask.model.Restaurant


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cricbuzztask.model.Restaurant.Review
import com.google.gson.annotations.SerializedName

@Entity(tableName = "restaurants")
data class ModelItem(
    @SerializedName("address")
    val address: String,
    @SerializedName("cuisine_type")
    val cuisineType: String,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("neighborhood")
    val neighborhood: String
)