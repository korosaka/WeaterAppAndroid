package com.example.weatherappandroid.repository.weather_info

import com.example.weatherappandroid.model.City
import com.example.weatherappandroid.model.Constants
import com.example.weatherappandroid.model.MyApplication
import com.example.weatherappandroid.model.WeatherInfo
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.roundToInt

class DummyWeatherInfoRepository : WeatherInfoRepository {

    override fun fetchWeatherInfo(id: String, completion: (WeatherInfo?) -> Unit) {
        val weatherInfo = extractWeatherInfo(getJsonObject(Constants.DUMMY_FILE_NAME))
        completion(weatherInfo)
    }

    private fun getJsonObject(fileName: String): JSONObject {
        val assetManager = MyApplication.instance.resources.assets
        val inputStream = assetManager.open(fileName)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        Thread.sleep(2000) // test
        return JSONObject(bufferedReader.readText())
    }

    private fun extractWeatherInfo(json: JSONObject): WeatherInfo? {
        var weatherInfo: WeatherInfo? = null

        try {
            val jsonArray = json.getJSONArray(Constants.WEATHER)
            val weatherJsonData = jsonArray.getJSONObject(0)
            val weatherMain = weatherJsonData.getString(Constants.WEATHER_MAIN)
            val description = weatherJsonData.getString(Constants.WEATHER_DESC)
            val temperature = convertIntoCelsius(weatherJsonData.getDouble(Constants.TEMP))

            weatherInfo = WeatherInfo(weatherMain, description, temperature)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return weatherInfo
    }

    private fun convertIntoCelsius(kelvin: Double): Int {
        return (kelvin - Constants.DIFF_KELVIN_CELSIUS).roundToInt()
    }
}