package com.example.cricbuzztask.model.Restaurant


import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("comments")
    val comments: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Int
)