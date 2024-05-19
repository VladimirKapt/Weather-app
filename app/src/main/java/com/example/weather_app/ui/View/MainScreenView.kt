package com.example.weather_app.ui.View

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_app.ui.ViewModel.MainScreenViewModel
import com.example.weather_app.ui.theme.WeatherappTheme

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreenView(mainScreenViewModel: MainScreenViewModel) {

    val locationText = mainScreenViewModel.locationText.value
    val localTempText = mainScreenViewModel.localTempText.value

    val day1 = mainScreenViewModel.day1.value
    val day2 = mainScreenViewModel.day2.value
    val day3 = mainScreenViewModel.day3.value
    val day4 = mainScreenViewModel.day4.value
    val day5 = mainScreenViewModel.day5.value
    val day6 = mainScreenViewModel.day6.value
    val day7 = mainScreenViewModel.day7.value

    val temp1 = mainScreenViewModel.temp1.value
    val temp2 = mainScreenViewModel.temp2.value
    val temp3 = mainScreenViewModel.temp3.value
    val temp4 = mainScreenViewModel.temp4.value
    val temp5 = mainScreenViewModel.temp5.value
    val temp6 = mainScreenViewModel.temp6.value
    val temp7 = mainScreenViewModel.temp7.value

    WeatherappTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .wrapContentHeight()
                        .padding(20.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .padding(top = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = locationText,
                            modifier = Modifier
                                .padding(16.dp),
                            fontSize = 30.sp
                        )
                        Text(
                            text = localTempText,
                            modifier = Modifier
                                .padding(16.dp),
                            fontSize = 30.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(top = 30.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(20.dp)
                        .wrapContentSize()
                        .border(2.dp, Color.Black)
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = day1,
                                modifier = Modifier.padding(start = 16.dp), // Отступ слева
                                fontSize = 25.sp
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = temp1,
                                modifier = Modifier.padding(end = 16.dp), // Отступ справа
                                fontSize = 25.sp
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = day2,
                                modifier = Modifier.padding(start = 16.dp), // Отступ слева
                                fontSize = 25.sp
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = temp2,
                                modifier = Modifier.padding(end = 16.dp), // Отступ справа
                                fontSize = 25.sp
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = day3,
                                modifier = Modifier.padding(start = 16.dp), // Отступ слева
                                fontSize = 25.sp
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = temp3,
                                modifier = Modifier.padding(end = 16.dp), // Отступ справа
                                fontSize = 25.sp
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = day4,
                                modifier = Modifier.padding(start = 16.dp), // Отступ слева
                                fontSize = 25.sp
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = temp4,
                                modifier = Modifier.padding(end = 16.dp), // Отступ справа
                                fontSize = 25.sp
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = day5,
                                modifier = Modifier.padding(start = 16.dp), // Отступ слева
                                fontSize = 25.sp
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = temp5,
                                modifier = Modifier.padding(end = 16.dp), // Отступ справа
                                fontSize = 25.sp
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = day6,
                                modifier = Modifier.padding(start = 16.dp), // Отступ слева
                                fontSize = 25.sp
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = temp6,
                                modifier = Modifier.padding(end = 16.dp), // Отступ справа
                                fontSize = 25.sp
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = day7,
                                modifier = Modifier.padding(start = 16.dp), // Отступ слева
                                fontSize = 25.sp
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = temp7,
                                modifier = Modifier.padding(end = 16.dp), // Отступ справа
                                fontSize = 25.sp
                            )
                        }


                    }
                }

            }
        }
    }
}