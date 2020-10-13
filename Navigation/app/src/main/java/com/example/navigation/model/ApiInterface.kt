package com.example.navigation.model

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("/public-api/users")
    fun getUserData(): Call<Users>

   /* @FormUrlEncoded
    @POST("/public-api/users")
    fun postUserData(@Field("name")name:String,
                     @Field("email")email:String,
                     @Field("gender")gender:String,
                     @Field("status")status:String):Call<Users>*/

    @Headers("Content-Type: application/json;charset=UTF-8")
    @PATCH("/public-api/users/{id}")
    fun updateuser(@Path("id")id:Int, @Body userpost:Data):Call<Data>


    @DELETE("/public-api/users/{id}")
    fun deleteuser(@Path("id")id:Int):Call<Unit>


}