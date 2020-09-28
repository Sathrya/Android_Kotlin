package com.example.navigation.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("status")
    var status: String,
    @SerializedName("updated_at")
    var updatedAt: String
)