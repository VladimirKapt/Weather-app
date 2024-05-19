package com.example.weather_app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weather_app.ui.Screens
import com.example.weather_app.ui.View.MainScreenView
import com.example.weather_app.ui.ViewModel.MainScreenViewModel
import com.example.weather_app.ui.theme.WeatherappTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val activity = this@MainActivity
            LaunchedEffect(activity) {
                activity.window.setBackgroundBlurRadius(15)
            }
            val mainScreenViewModel: MainScreenViewModel = viewModel {
                MainScreenViewModel(this@MainActivity) }

            WeatherappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screens.MAIN_SCREEN
                    ){
                        composable(Screens.MAIN_SCREEN){
                            MainScreenView(
                                mainScreenViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}