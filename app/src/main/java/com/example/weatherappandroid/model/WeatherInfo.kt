package com.example.weatherappandroid.model

class WeatherInfo(val cityName: String = Constants.EMPTY_TEXT,
                  val weather: String = Constants.EMPTY_TEXT,
                  val description: String = Constants.EMPTY_TEXT,
                  val temperature: Int = 0)