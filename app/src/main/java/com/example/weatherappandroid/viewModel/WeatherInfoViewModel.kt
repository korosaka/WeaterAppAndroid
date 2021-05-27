package com.example.weatherappandroid.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappandroid.model.WeatherInfo
import com.example.weatherappandroid.repository.weather_info.DummyWeatherInfoRepository
import com.example.weatherappandroid.repository.weather_info.WeatherInfoRepository
import kotlinx.coroutines.*

class WeatherInfoViewModel : ViewModel() {

    var cityId: String? = null
    private val weatherInfoRepo: WeatherInfoRepository
    var weatherInfo: MutableLiveData<WeatherInfo> = MutableLiveData()

    init {
        //TODO switch to APIRepo
        weatherInfoRepo = DummyWeatherInfoRepository()
    }

    fun fetchWeatherByRx() {
        weatherInfo.value = WeatherInfo()
        if (cityId == null) return // TODO some functions to show error on UI (EX: error dialog)
        weatherInfoRepo.fetchWeatherByRx(cityId!!)
            .subscribe { weather ->
                //TODO handling when weather is null
                weatherInfo.value = weather

                //TODO should stop(quit) subscription, (about memory leak)?
            }
    }

    fun fetchWeatherByCoroutine() {
        weatherInfo.value = WeatherInfo()
        if (cityId == null) return // TODO some functions to show error on UI
        viewModelScope.launch(Dispatchers.IO) {
            val weather = weatherInfoRepo.fetchWeatherByCoroutine(cityId!!)
            withContext(Dispatchers.Main) {
                weatherInfo.value = weather
            }
        }
    }

}