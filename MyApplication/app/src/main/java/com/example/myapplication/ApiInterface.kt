package com.example.myapplication

import com.example.myapplication.model.Data
import com.example.myapplication.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/public-api/users")
    fun getUserData(): Call<Users>
}