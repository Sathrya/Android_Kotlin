package com.example.navigation.model


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("limit")
    var limit: Int,
    @SerializedName("page")
    var page: Int,
    @SerializedName("pages")
    var pages: Int,
    @SerializedName("total")
    var total: Int
)