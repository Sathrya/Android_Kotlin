package com.example.cricbuzztask.model.Restaurant


import com.google.gson.annotations.SerializedName

data class Latlng(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)