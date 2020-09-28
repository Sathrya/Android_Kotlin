package com.example.navigation.model

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetroFitInstance {
     val token="ea21d95f84eabd3b187797f893baf3ae2377e319be202ce6562b4c5b9f208dad"
    private var client: OkHttpClient = OkHttpClient.Builder().addInterceptor(object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", " Bearer $token")
                .build()
            return chain.proceed(newRequest)
        }
    }).build()

    private var baseUrl="https://gorest.co.in/public-api/"
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}