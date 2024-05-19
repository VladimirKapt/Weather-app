package com.example.weather_app.ui.ViewModel

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.weather_app.MainActivity
import com.example.weather_app.data.dto.ForecastWeatherResponceDto
import com.example.weather_app.domain.GetCurrentWeatherUseCase
import com.example.weather_app.domain.GetForecastWeatherUseCase
import com.example.weather_app.domain.entites.Status
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@RequiresApi(Build.VERSION_CODES.O)
class MainScreenViewModel(private val activity: MainActivity): ViewModel() {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
    private val locationRequest: LocationRequest = LocationRequest.create().apply {
        interval = 10000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
    private val REQUEST_LOCATION_PERMISSION = 1

    val apiKey = "a1fb0d77fb254bb592093919241905"
    val getCurrentWeatherUseCase = GetCurrentWeatherUseCase()
    val getForecastWeatherUseCase = GetForecastWeatherUseCase()

    val locationText = mutableStateOf("Загрузка...")
    val localTempText = mutableStateOf("-")

    val day1 = mutableStateOf("-")
    val day2 = mutableStateOf("-")
    val day3 = mutableStateOf("-")
    val day4 = mutableStateOf("-")
    val day5 = mutableStateOf("-")
    val day6 = mutableStateOf("-")
    val day7 = mutableStateOf("-")

    val temp1 = mutableStateOf("-")
    val temp2 = mutableStateOf("-")
    val temp3 = mutableStateOf("-")
    val temp4 = mutableStateOf("-")
    val temp5 = mutableStateOf("-")
    val temp6 = mutableStateOf("-")
    val temp7 = mutableStateOf("-")

    private var coordinates: String? = null

    init {
        getCurrentWeather()
        getForecastWeather()
    }
    private suspend fun getCurrentLocation(): String = suspendCoroutine { continuation ->
        if (coordinates != null) {
            continuation.resumeWith(Result.success(coordinates!!))
            return@suspendCoroutine
        }

        if (ContextCompat.checkSelfPermission(
                activity, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED
        ){
            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    locationResult ?: return
                    for (location in locationResult.locations){
                        coordinates = "${location.latitude},${location.longitude}"
                        try {
                            continuation.resumeWith(Result.success(coordinates!!))
                        } catch (e: IllegalStateException) {
                            // корутина уже была возобновлена, ничего не делаем
                        }
                        break
                    }
                }
            }

            if (ContextCompat.checkSelfPermission(
                    activity, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED
            ){
                activity.runOnUiThread {
                    fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
                }
            } else {
                requestLocationPermission()
                continuation.resumeWith(Result.success("Permission not granted"))
            }
        } else {
            requestLocationPermission()
            continuation.resumeWith(Result.success("Permission not granted"))
        }
    }


    private fun getCurrentWeather(){
        CoroutineScope(Dispatchers.IO).launch {
            val coordinates = getCurrentLocation()
            Log.w("кординаты:",coordinates)
            var attempts = 0
            while(attempts < 3) {
                val status = getCurrentWeatherUseCase.getCurrentWeather(apiKey, coordinates)
                withContext(Dispatchers.Main) {
                    when (status) {
                        is Status.Success<*> -> {
                            // Обработка успешного ответа
                            val weatherResponse = status.data
                            Log.w("ОТВЕТ", "В ${weatherResponse?.location?.name.toString()} температура ${weatherResponse?.current?.temp_c.toString()}")
                            locationText.value = weatherResponse?.location?.name.toString()
                            localTempText.value = weatherResponse?.current?.temp_c.toString() + "°"
                            attempts = 3
                            return@withContext
                        }
                        is Status.Error<*> -> {
                            // Обработка ошибки
                            val errorMessage = status.message
                            Log.w("ОШИБКА",errorMessage.toString())

                            attempts++
                            if(attempts < 3) {
                                delay(2000)
                            }
                            else{
                                locationText.value = errorMessage.toString()
                            }
                        }
                        is Status.Loading<*> -> {
                            Log.w("СТАТУС: ЗАГРУЗКА","загрузка")
                        }
                    }
                }
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDayOfWeek(date: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val localDate = LocalDate.parse(date, formatter)
        return localDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getForecastWeather() {
        CoroutineScope(Dispatchers.IO).launch {
            val coordinates = getCurrentLocation()
            Log.w("координаты:", coordinates)
            var attempts = 0
            while (attempts < 3) {
                val status = getForecastWeatherUseCase.getForecastWeather(apiKey, coordinates, 7)
                withContext(Dispatchers.Main) {
                    when (status) {
                        is Status.Success<*> -> {
                            // Обработка успешного ответа
                            val weatherResponse = status.data as ForecastWeatherResponceDto
                            Log.w("ОТВЕТ", "Прогноз погоды для ${weatherResponse.forecast.forecastday.size} дней получен")
                            weatherResponse.forecast.forecastday.forEachIndexed { index, forecastDay ->
                                val dayOfWeek = getDayOfWeek(forecastDay.date)
                                when (index) {
                                    0 -> {
                                        day1.value = dayOfWeek
                                        temp1.value = "${forecastDay.day.maxtemp_c}°"
                                    }
                                    1 -> {
                                        day2.value = dayOfWeek
                                        temp2.value = "${forecastDay.day.maxtemp_c}°"
                                    }
                                    2 -> {
                                        day3.value = dayOfWeek
                                        temp3.value = "${forecastDay.day.maxtemp_c}°"
                                    }
                                    3 -> {
                                        day4.value = dayOfWeek
                                        temp4.value = "${forecastDay.day.maxtemp_c}°"
                                    }
                                    4 -> {
                                        day5.value = dayOfWeek
                                        temp5.value = "${forecastDay.day.maxtemp_c}°"
                                    }
                                    5 -> {
                                        day6.value = dayOfWeek
                                        temp6.value = "${forecastDay.day.maxtemp_c}°"
                                    }
                                    6 -> {
                                        day7.value = dayOfWeek
                                        temp7.value = "${forecastDay.day.maxtemp_c}°"
                                    }
                                }
                            }
                            attempts = 3
                            return@withContext
                        }
                        is Status.Error<*> -> {
                            // Обработка ошибки
                            val errorMessage = status.message
                            Log.w("ОШИБКА", errorMessage.toString())

                            attempts++
                            if (attempts < 3) {
                                delay(2000)
                            }
                        }
                        is Status.Loading<*> -> {
                            Log.w("СТАТУС: ЗАГРУЗКА", "загрузка")
                        }
                    }
                }
            }
        }
    }


    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            AlertDialog.Builder(activity)
                .setTitle("Необходимо разрешение на доступ к местоположению")
                .setMessage("Это приложение требует вашего разрешения на доступ к местоположению, чтобы показать текущую погоду.")
                .setPositiveButton("ОК") { _, _ ->
                    ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
                }
                .create()
                .show()
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
        }
    }
}




