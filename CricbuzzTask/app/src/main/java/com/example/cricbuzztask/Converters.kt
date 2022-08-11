package com.example.cricbuzztask

import androidx.room.TypeConverter
import com.example.cricbuzztask.model.Restaurant.Review
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken


class Converters{

    @TypeConverter
    fun listToJson(review: List<Review>) = Gson().toJson(review)

    @TypeConverter
    fun JsonToList(review:String)= Gson().fromJson(review,Array<Review>::class.java).toList()

}