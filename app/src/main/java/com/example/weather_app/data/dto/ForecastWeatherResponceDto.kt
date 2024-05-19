package com.example.weather_app.data.dto

data class ForecastWeatherResponceDto(
    val forecast: Forecast
) {
    data class Forecast(
        val forecastday: List<ForecastDay>
    )

    data class ForecastDay(
        val date: String,
        val day: Day
    )

    data class Day(
        val maxtemp_c: Double,
        val mintemp_c: Double,
        val avgtemp_c: Double
    )
}