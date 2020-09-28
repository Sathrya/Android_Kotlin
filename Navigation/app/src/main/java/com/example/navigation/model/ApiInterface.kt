package com.example.navigation.model

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("/public-api/users")
    fun getUserData(): Call<Users>
    /*@POST("/public-api/users")
    fun postUserData(@Body userpost: UsersPost):Call<UsersPost>
    @DELETE("/public-api/users/{id}")/*  @Headers("Content-Type: application/json;charset=UTF-8")
    @PATCH("/public-api/users/{id}")
    fun updateuser(@Path("id")id:Int,
                   @Header("Authorization") token:String,
                   @Body userpost:UsersPost):Call<Users>*/
    fun deleteuser(@Path("id")id:Int):Call<Unit>*/


}