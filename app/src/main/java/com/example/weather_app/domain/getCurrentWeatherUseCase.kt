package com.example.weather_app.domain

import com.example.weather_app.data.dto.WeatherResponse
import com.example.weather_app.data.source.WeatherApi
import com.example.weather_app.domain.entites.Status
import com.example.weather_app.network.RetrofitFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class GetCurrentWeatherUseCase {

    val weatherApi = RetrofitFactory().retrofit.create(WeatherApi::class.java)

    suspend fun getCurrentWeather(apiKey: String, location: String): Status<WeatherResponse> {
        return try {
            val response = weatherApi.getCurrentWeather(apiKey, location)
            if (response.isSuccessful) {
                Status.Success(response.body()!!)
            } else {
                Status.Error("Error: ${response.code()}", null)
            }
        } catch (e: Exception) {
            Status.Error("Failure: ${e.message}", null)
        }
    }
}