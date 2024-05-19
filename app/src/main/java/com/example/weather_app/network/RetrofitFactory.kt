package com.example.weather_app.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory {
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.MINUTES) //  время ожидания подключения
        .readTimeout(30, TimeUnit.SECONDS) //  время ожидания чтения
        .writeTimeout(15, TimeUnit.SECONDS) //  время ожидания записи
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://api.weatherapi.com/v1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}