package com.example.weather_app.domain

import com.example.weather_app.data.dto.ForecastWeatherResponceDto
import com.example.weather_app.data.dto.WeatherResponse
import com.example.weather_app.data.source.WeatherApi
import com.example.weather_app.domain.entites.Status
import com.example.weather_app.network.RetrofitFactory

class GetForecastWeatherUseCase {
    val weatherApi = RetrofitFactory().retrofit.create(WeatherApi::class.java)

    suspend fun getForecastWeather(apiKey: String, location: String, days: Int): Status<ForecastWeatherResponceDto> {
        return try {
            val response = weatherApi.getForecastWeather(apiKey, location, days)
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