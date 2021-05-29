package com.example.weatherappandroid.model

class Constants {
    companion object {
        const val WEATHER = "weather"
        const val CITY_NAME = "name"
        const val WEATHER_MAIN = "main"
        const val WEATHER_DESC = "description"
        const val MAIN_OBJECT = "main"
        const val TEMP = "temp"
        const val DUMMY_FILE_NAME = "DummyWeatherInfo.json"
        const val DIFF_KELVIN_CELSIUS = 273.15

        const val EMPTY = ""
        const val EMPTY_TEXT = "----"
        const val CITY_ID = "cityId"
        const val ASYNC_TYPE = "asyncType"


        /**
         * API
         * detail: https://openweathermap.org/api
         */
        const val API_BASE_URL = "http://api.openweathermap.org/data/2.5/"
        const val API_KEY = "6b19405ec895be3eeaab81074be2cf95"
        const val API_VALUE = "weather"
        const val API_ID_PARAM = "id"
        const val API_KEY_PARAM = "appid"
    }
}