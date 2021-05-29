package com.example.weatherappandroid.repository.weather_info

import com.example.weatherappandroid.model.WeatherInfo
import io.reactivex.Observable

interface WeatherInfoRepository {
    fun fetchWeatherByRx(id: String): Observable<WeatherInfo?>
    suspend fun fetchWeatherByCoroutine(id: String): WeatherInfo?
}