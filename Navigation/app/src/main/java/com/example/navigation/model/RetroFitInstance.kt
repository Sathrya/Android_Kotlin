package com.example.navigation.model

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetroFitInstance {

    var base_url="https://gorest.co.in/public-api/"
    fun getRetrofitInstance(): Retrofit {

        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

}