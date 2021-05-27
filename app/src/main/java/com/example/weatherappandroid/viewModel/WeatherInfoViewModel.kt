package com.example.weatherappandroid.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherappandroid.model.WeatherInfo
import com.example.weatherappandroid.repository.weather_info.DummyWeatherInfoRepository
import com.example.weatherappandroid.repository.weather_info.WeatherInfoRepository

class WeatherInfoViewModel : ViewModel() {

    var cityId: String? = null
    private val weatherInfoRepo: WeatherInfoRepository
    var weatherInfo: MutableLiveData<WeatherInfo> = MutableLiveData()

    init {
        //TODO switch to APIRepo
        weatherInfoRepo = DummyWeatherInfoRepository()
    }

    fun fetchWeatherInfo() {
        weatherInfo.value = WeatherInfo()
        if (cityId == null) return // TODO some functions to show error on UI (EX: error dialog)
        weatherInfoRepo.fetchWeatherInfo(cityId!!)
            .subscribe { weather ->
                //TODO handling when weather is null
                weatherInfo.value = weather

                //TODO should stop(quit) subscription, (about memory leak)?
            }
    }

}