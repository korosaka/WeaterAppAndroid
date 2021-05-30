package com.example.weatherappandroid.repository.weather_info

import com.example.weatherappandroid.model.WeatherInfo
import io.reactivex.Observable

interface WeatherInfoRepository {
    fun fetchWeatherByRx(cityId: String): Observable<WeatherInfo?>
    suspend fun fetchWeatherByCoroutine(cityId: String): WeatherInfo?
}