package com.example.weatherappandroid.repository.weather_info

import com.example.weatherappandroid.model.Constants
import com.example.weatherappandroid.model.MyApplication
import com.example.weatherappandroid.model.WeatherInfo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.roundToInt

class DummyWeatherInfoRepository : WeatherInfoRepository {

    override fun fetchWeatherInfo(id: String): Observable<WeatherInfo?> {

        /**
         * without "defer", getJsonObject function run immediately on Main Thread before subscription
         * ref: https://proandroiddev.com/from-rxjava-2-to-kotlin-flow-threading-8618867e1955
         */
        return Observable.defer { Observable.just(getJsonObject(Constants.DUMMY_FILE_NAME)) }
            .subscribeOn(io()) // to change thread for the process done by subscribe()
            .observeOn(AndroidSchedulers.mainThread()) // to use the result on Main thread
            .map {
                extractWeatherInfo(it)
            }
    }

    private fun getJsonObject(fileName: String): JSONObject {
        val assetManager = MyApplication.instance.resources.assets
        val inputStream = assetManager.open(fileName)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        Thread.sleep(2000) // without async, in this time, view will be never shown
        return JSONObject(bufferedReader.readText())
    }

    private fun extractWeatherInfo(json: JSONObject): WeatherInfo? {
        var weatherInfo: WeatherInfo? = null

        try {
            val cityName = json.getString(Constants.CITY_NAME)
            val jsonArray = json.getJSONArray(Constants.WEATHER)
            val weatherJsonData = jsonArray.getJSONObject(0)
            val weatherMain = weatherJsonData.getString(Constants.WEATHER_MAIN)
            val description = weatherJsonData.getString(Constants.WEATHER_DESC)
            val main = json.getJSONObject(Constants.MAIN_OBJECT)
            val temperature = convertIntoCelsius(main.getDouble(Constants.TEMP))

            weatherInfo = WeatherInfo(cityName, weatherMain, description, temperature.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return weatherInfo
    }

    private fun convertIntoCelsius(kelvin: Double): Int {
        return (kelvin - Constants.DIFF_KELVIN_CELSIUS).roundToInt()
    }
}