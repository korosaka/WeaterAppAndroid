package com.example.weatherappandroid.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherappandroid.R
import com.example.weatherappandroid.model.Constants


class WeatherInfoFragment : Fragment() {
    private var cityId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cityId = it.getString(Constants.CITY_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_info, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(cityId: String) =
            WeatherInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.CITY_ID, cityId)
                }
            }
    }
}