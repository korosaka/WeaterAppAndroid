package com.example.weatherappandroid.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.weatherappandroid.R
import com.example.weatherappandroid.databinding.FragmentWeatherInfoBinding
import com.example.weatherappandroid.model.Constants
import com.example.weatherappandroid.viewModel.WeatherInfoViewModel


class WeatherInfoFragment : Fragment() {
    private var cityId: String? = null
    private lateinit var binding: FragmentWeatherInfoBinding
    private val viewModel: WeatherInfoViewModel by lazy {
        ViewModelProviders.of(this).get(WeatherInfoViewModel::class.java)
    }

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
        substituteBinding(inflater, container)
        return binding.root
    }

    //TODO this function is created even in CityListFragment
    private fun substituteBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_weather_info, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
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