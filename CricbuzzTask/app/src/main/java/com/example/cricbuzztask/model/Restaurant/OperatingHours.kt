package com.example.cricbuzztask.model.Restaurant


import com.google.gson.annotations.SerializedName

data class OperatingHours(
    @SerializedName("Friday")
    val friday: String,
    @SerializedName("Monday")
    val monday: String,
    @SerializedName("Saturday")
    val saturday: String,
    @SerializedName("Sunday")
    val sunday: String,
    @SerializedName("Thursday")
    val thursday: String,
    @SerializedName("Tuesday")
    val tuesday: String,
    @SerializedName("Wednesday")
    val wednesday: String
)