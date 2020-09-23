package com.example.myapplication.model


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("pagination")
    var pagination: Pagination
)