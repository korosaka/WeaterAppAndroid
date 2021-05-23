package com.example.weatherappandroid.repository.weather_info

import com.example.weatherappandroid.model.City
import com.example.weatherappandroid.model.WeatherInfo

interface WeatherInfoRepository {
    fun fetchWeatherInfo(id: String, completion: (WeatherInfo?) -> Unit)
}