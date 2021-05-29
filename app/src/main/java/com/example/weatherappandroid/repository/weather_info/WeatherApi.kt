package com.example.weatherappandroid.repository.weather_info

import com.example.weatherappandroid.model.Constants
import com.example.weatherappandroid.model.entity.WeatherAPIEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    //ref: https://yutaabe200.hatenablog.com/entry/2018/07/25/%E3%80%90Kotlin%E3%80%91Retrofit2_0%E3%81%AEQuery%E3%83%91%E3%83%A9%E3%83%A1%E3%83%BC%E3%82%BF%E3%81%AE%E4%BD%BF%E3%81%84%E6%96%B9%5B%E5%82%99%E5%BF%98%E9%8C%B2%5D
    @GET(Constants.API_VALUE)
    fun fetchWeather(@Query(Constants.API_ID_PARAM) cityId: String,
                     @Query(Constants.API_KEY_PARAM) apiKey: String): Call<WeatherAPIEntity>
}