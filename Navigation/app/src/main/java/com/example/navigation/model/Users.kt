package com.example.navigation.model


import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("code")
    var code: Int,
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("meta")
    var meta: Meta
)