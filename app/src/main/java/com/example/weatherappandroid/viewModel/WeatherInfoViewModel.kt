package com.example.weatherappandroid.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherappandroid.model.Constants
import com.example.weatherappandroid.model.WeatherInfo
import com.example.weatherappandroid.repository.weather_info.DummyWeatherInfoRepository
import com.example.weatherappandroid.repository.weather_info.WeatherInfoRepository

class WeatherInfoViewModel() : ViewModel() {

    var cityId: String? = null
    private val weatherInfoRepo: WeatherInfoRepository
    var weatherInfo: MutableLiveData<WeatherInfo> = MutableLiveData()

    init {
        //TODO switch to APIRepo
        weatherInfoRepo = DummyWeatherInfoRepository()
    }

    fun fetchWeatherInfo() {
        weatherInfo.value = WeatherInfo()
        val completionHandler: (WeatherInfo?) -> Unit = {
//            Thread.sleep(5000) // without async, in this time, view will be never shown
            if (it != null) {
                weatherInfo.value = it
            } else {
                // TODO some functions to show error on UI (EX: error dialog)
                println("fetching data was failed")
                weatherInfo.value = WeatherInfo()
            }
        }
        //TODO should do null handling
        if (cityId != null) weatherInfoRepo.fetchWeatherInfo(
            cityId!!,
            completionHandler
        )
    }

    fun getTempString(): String {
        if(weatherInfo.value == null) return Constants.EMPTY_TEXT
        return weatherInfo.value?.temperature.toString()
    }
}