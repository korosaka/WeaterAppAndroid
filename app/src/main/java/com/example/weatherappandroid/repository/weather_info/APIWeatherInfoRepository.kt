package com.example.weatherappandroid.repository.weather_info

import com.example.weatherappandroid.model.Constants
import com.example.weatherappandroid.model.WeatherInfo
import com.example.weatherappandroid.model.entity.WeatherAPIEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import kotlin.math.roundToInt

class APIWeatherInfoRepository : WeatherInfoRepository {

    override fun fetchWeatherByRx(cityId: String): Observable<WeatherInfo?> {
        return Observable.defer {
            Observable.just(
                executeAPI(cityId)
            )
        }
            .subscribeOn(Schedulers.io()) // to change thread for the process done by subscribe()
            .observeOn(AndroidSchedulers.mainThread()) // to use the result on Main thread
            .map {
                extractWeatherInfo(it.body()) ?: throw IOException("failed to fetch weather data with API")
            }
    }

    private fun executeAPI(id: String): Response<WeatherAPIEntity> {
        val service = restClient().create(WeatherApi::class.java)
        return service.fetchWeather(id, Constants.API_KEY).execute()
    }

    private fun restClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun extractWeatherInfo(entity: WeatherAPIEntity?): WeatherInfo? {
        var weatherInfo: WeatherInfo? = null

        val entityData = entity ?: return weatherInfo
        try {
            val cityName = entityData.name
            val description = entityData.weather[0].description
            val weatherMain = entityData.weather[0].main
            val temperature = convertIntoCelsius(entityData.main.temp)

            weatherInfo = WeatherInfo(cityName, weatherMain, description, temperature.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return weatherInfo
    }

    override suspend fun fetchWeatherByCoroutine(cityId: String): WeatherInfo? {
        //TODO
        println("Not yet implemented")
        return null
    }

    //TODO Duplicate
    private fun convertIntoCelsius(kelvin: Double): Int {
        return (kelvin - Constants.DIFF_KELVIN_CELSIUS).roundToInt()
    }

}