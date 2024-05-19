package com.example.weather_app.data.dto

data class WeatherResponse(
    val location: Location,
    val current: Current
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double
)

data class Current(
    val temp_c: Double
)
