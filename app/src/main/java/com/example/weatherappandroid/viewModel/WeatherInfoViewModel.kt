package com.example.weatherappandroid.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappandroid.model.Constants
import com.example.weatherappandroid.model.WeatherInfo
import com.example.weatherappandroid.repository.weather_info.APIWeatherInfoRepository
import com.example.weatherappandroid.repository.weather_info.DummyWeatherInfoRepository
import com.example.weatherappandroid.repository.weather_info.WeatherInfoRepository
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.*
import org.reactivestreams.Subscriber
import java.util.concurrent.Flow

class WeatherInfoViewModel : ViewModel() {

    var cityId: String? = null
    var asyncType: AsyncType? = null
    private val weatherInfoRepo: WeatherInfoRepository
    var weatherInfo: MutableLiveData<WeatherInfo> = MutableLiveData()
    var testText: MutableLiveData<String> = MutableLiveData()

    init {
//        weatherInfoRepo = DummyWeatherInfoRepository() //Dummy Repo (Local)
        weatherInfoRepo = APIWeatherInfoRepository()
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
            .subscribe(object : Observer<WeatherInfo?> {
                override fun onComplete() {}
                override fun onNext(t: WeatherInfo) {
                    weatherInfo.value = t
                    testText.value = "The data has fetched using Rx."
                }

                override fun onError(e: Throwable) {
                    testText.value = "Error is happen in fetching data by Rx\n$e"
                    // TODO error dialog should be shown
                }

                override fun onSubscribe(d: Disposable) {}
            })
    }

    private fun fetchWeatherByCoroutine() {
        if (cityId == null) return // TODO some functions to show error on UI
        viewModelScope.launch(Dispatchers.IO) {
            val weather = weatherInfoRepo.fetchWeatherByCoroutine(cityId!!)
            // the process related to UI must be on Main Thread
            withContext(Dispatchers.Main) {
                weatherInfo.value = weather
                testText.value = "The data has fetched using Coroutine."
            }
        }
    }

}