package com.example.weather_app.domain.entites

sealed class Status<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Status<T>(data)
    class Loading<T>(data: T? = null) : Status<T>(data)
    class Error<T>(message: String, data: T? = null) : Status<T>(data, message)
}