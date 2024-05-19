package com.example.weather_app.data.source

import com.example.weather_app.data.dto.ForecastWeatherResponceDto
import com.example.weather_app.data.dto.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String,
    ): Response<WeatherResponse>

    @GET("forecast.json")
    suspend fun getForecastWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int,
    ): Response<ForecastWeatherResponceDto>
}