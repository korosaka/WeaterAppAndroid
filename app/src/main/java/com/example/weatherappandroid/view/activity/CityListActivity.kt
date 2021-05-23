package com.example.weatherappandroid.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherappandroid.R
import com.example.weatherappandroid.view.fragment.CityListFragment
import com.example.weatherappandroid.viewModel.CityListViewModel

class CityListActivity : AppCompatActivity() {

    private lateinit var viewModel: CityListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_list)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, CityListFragment())
                .commit()
        }
    }
}