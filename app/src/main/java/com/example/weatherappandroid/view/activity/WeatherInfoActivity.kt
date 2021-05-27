package com.example.weatherappandroid.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherappandroid.R
import com.example.weatherappandroid.model.Constants
import com.example.weatherappandroid.view.fragment.CityListFragment
import com.example.weatherappandroid.view.fragment.WeatherInfoFragment
import com.example.weatherappandroid.viewModel.AsyncType

class WeatherInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_info)

        if (savedInstanceState == null) {
            val cityId = intent.getStringExtra(Constants.CITY_ID)
                ?: Constants.EMPTY //TODO should do some null handling!
            val asyncType = intent.getSerializableExtra(Constants.ASYNC_TYPE)
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.container,
                    WeatherInfoFragment.newInstance(cityId, asyncType as AsyncType)
                )
                .commit()
        }
    }
}