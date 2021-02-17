package com.example.apicalls.model


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("pagination")
    var pagination: Pagination
)