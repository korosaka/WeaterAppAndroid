package com.example.weatherappandroid.repository.weather_info

import com.example.weatherappandroid.model.City
import com.example.weatherappandroid.model.WeatherInfo
import io.reactivex.Observable
import io.reactivex.Single

interface WeatherInfoRepository {
    fun fetchWeatherInfo(id: String): Observable<WeatherInfo?>
}