package com.example.weatherappandroid.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherappandroid.R
import com.example.weatherappandroid.model.City
import com.example.weatherappandroid.model.Constants
import com.example.weatherappandroid.repository.CityRepository

/**
 * ref: https://developer.android.com/topic/libraries/architecture/viewmodel
 */
class CityListViewModel : ViewModel() {

    var filterWord: MutableLiveData<String> = MutableLiveData()

    //TODO should be immutable??(List)
    private var cityList: MutableList<City> = ArrayList()
    private var filteredCityList: MutableLiveData<MutableList<City>> = MutableLiveData()
    var clickLister: ClickItemListener? = null // like Delegate in Swift
    var testText: MutableLiveData<String> = MutableLiveData()
    var radioChecked = MutableLiveData<Int>()
    private val defaultRadioChecked = R.id.button_coroutine

    //TODO getter shouldn't be used?? (Can filteredCityList be used?)
    fun getFilteredCityList(): LiveData<MutableList<City>> {
        return filteredCityList
    }

    fun updateTestText() {
        var testString = "test: "

        for (city in filteredCityList.value ?: arrayListOf()) {
            testString += city.name
        }
        testText.value = testString
    }

    init {
        filterWord.value = Constants.EMPTY
        radioChecked.value = defaultRadioChecked
        fetchCityData()
    }

    private fun fetchCityData() {
        val completionHandler: (MutableList<City>) -> Unit = { cities ->
            cityList = cities
            updateFilter()
            updateTestText()
        }
        CityRepository().fetchCities(completionHandler)
    }

    private fun filterCityList(): MutableList<City>? {
        val filterWordValue = filterWord.value ?: return cityList
        if (filterWordValue == "") return cityList

        var filteredList: MutableList<City> = ArrayList()

        for (city in cityList) {
            if (city.country.toUpperCase().contains(filterWordValue.toUpperCase()) ||
                city.name.toUpperCase().contains(filterWordValue.toUpperCase())
            ) {
                filteredList.add(
                    city
                )
            }
        }
        return filteredList
    }

    fun updateFilter() {
        filteredCityList.value = filterCityList()
    }

    fun getFilteredCityCount(): Int = filteredCityList.value?.size ?: 0

    fun getCity(position: Int): City = filteredCityList.value?.get(position)
        ?: throw IllegalStateException("CityList is not Initialized")

    fun onClickCity(item: City) {
        clickLister?.onClickCityItem(item) ?: println("test: listener is null")
    }

    fun getAsyncType(): AsyncType {
        return when (radioChecked.value) {
            defaultRadioChecked -> AsyncType.COROUTINE
            else -> AsyncType.RX
        }
    }
}

interface ClickItemListener {
    fun onClickCityItem(item: City)
}

enum class AsyncType {
    RX, COROUTINE
}