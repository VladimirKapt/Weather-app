package com.example.weather_app.domain

import android.telecom.Call
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather_app.domain.entites.Status
import javax.security.auth.callback.Callback

interface WeatherRepository {
    fun getCurrentWeather() {
    }
    fun getForecastWeather()

}