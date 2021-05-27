package com.example.weatherappandroid.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappandroid.model.Constants
import com.example.weatherappandroid.model.WeatherInfo
import com.example.weatherappandroid.repository.weather_info.DummyWeatherInfoRepository
import com.example.weatherappandroid.repository.weather_info.WeatherInfoRepository
import kotlinx.coroutines.*

class WeatherInfoViewModel : ViewModel() {

    var cityId: String? = null
    var asyncType: AsyncType? = null
    private val weatherInfoRepo: WeatherInfoRepository
    var weatherInfo: MutableLiveData<WeatherInfo> = MutableLiveData()
    var testText: MutableLiveData<String> = MutableLiveData()

    init {
        //TODO switch to APIRepo
        weatherInfoRepo = DummyWeatherInfoRepository()
        testText.value = Constants.EMPTY
    }

    fun fetchWeather() {
        weatherInfo.value = WeatherInfo()
        when (asyncType) {
            AsyncType.COROUTINE -> fetchWeatherByCoroutine()
            AsyncType.RX -> fetchWeatherByRx()
            else -> return // TODO null(error) handling
        }
    }

    private fun fetchWeatherByRx() {
        if (cityId == null) return // TODO some functions to show error on UI (EX: error dialog)
        weatherInfoRepo.fetchWeatherByRx(cityId!!)
            .subscribe { weather ->
                //TODO handling when weather is null
                weatherInfo.value = weather
                testText.value = "The data has fetched by Rx."
                //TODO should stop(quit) subscription, (about memory leak)?
            }
    }

    private fun fetchWeatherByCoroutine() {
        if (cityId == null) return // TODO some functions to show error on UI
        viewModelScope.launch(Dispatchers.IO) {
            val weather = weatherInfoRepo.fetchWeatherByCoroutine(cityId!!)
            // the process related to UI must be on Main Thread
            withContext(Dispatchers.Main) {
                weatherInfo.value = weather
                testText.value = "The data has fetched by Coroutine."
            }
        }
    }

}