package com.example.weatherappandroid.repository

import com.example.weatherappandroid.model.City

class CityRepository {

    private val cityList: MutableList<City> = mutableListOf(
        City("5814616", "CANADA", "Vancouver"),
        City("6167865", "CANADA", "Toronto"),
        City("6077243", "CANADA", "Montréal"),
        City("5913490", "CANADA", "Calgary"),
        City("6094817", "CANADA", "Ottawa"),
        City("1850147", "JAPAN", "Tokyo"),
        City("1853908", "JAPAN", "Osaka"),
        City("2130037", "JAPAN", "Hokkaido"),
        City("1863967", "JAPAN", "Fukuoka"),
        City("1854345", "JAPAN", "Okinawa"),
        City("error_id", "JAPAN", "ERROR CITY") //test
    )

    fun fetchCities(handler: (MutableList<City>) -> Unit) {
        handler(cityList)
    }
}