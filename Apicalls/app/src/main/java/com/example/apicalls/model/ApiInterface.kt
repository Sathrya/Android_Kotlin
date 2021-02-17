package com.example.apicalls.model


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/public-api/users")
    fun getUserData(@Query("page")page:Int): Call<Users>
}