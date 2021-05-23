package com.example.weatherappandroid.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherappandroid.model.WeatherInfo
import com.example.weatherappandroid.repository.weather_info.DummyWeatherInfoRepository
import com.example.weatherappandroid.repository.weather_info.WeatherInfoRepository

class WeatherInfoViewModel(private val cityId: String) : ViewModel() {

    private val weatherInfoRepo: WeatherInfoRepository
    var weatherInfo: MutableLiveData<WeatherInfo> = MutableLiveData()

    init {
        //TODO switch to APIRepo
        weatherInfoRepo = DummyWeatherInfoRepository()
        fetchWeatherInfo()
    }

    private fun fetchWeatherInfo() {
        weatherInfo.value = WeatherInfo()
        val completionHandler: (WeatherInfo?) -> Unit = {
            if(it != null) weatherInfo.value = it
            else println("fetching data was failed") // TODO some functions to show error on UI
        }
        weatherInfoRepo.fetchWeatherInfo(cityId, completionHandler)
    }
}