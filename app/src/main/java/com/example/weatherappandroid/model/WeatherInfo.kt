package com.example.weatherappandroid.model

import java.sql.Time

class WeatherInfo(val weather: String = Constants.EMPTY_TEXT,
                  val description: String = Constants.EMPTY_TEXT,
                  val temperature: Int = 0)