package com.example.weatherappandroid.model.entity

data class WeatherAPIEntity(val weather: Array<Weather>,
                            val main: Main,
                            val name: String)

data class Weather(val main: String, val description: String)

data class Main(val temp: Double)