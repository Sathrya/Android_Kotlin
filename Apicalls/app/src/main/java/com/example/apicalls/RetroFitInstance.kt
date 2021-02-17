package com.example.apicalls

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitInstance {

    var base_url="https://gorest.co.in/"
    fun getRetrofitInstance(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

}