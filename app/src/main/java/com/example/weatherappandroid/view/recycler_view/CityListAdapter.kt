package com.example.weatherappandroid.view.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappandroid.R
import com.example.weatherappandroid.model.City

class CityListAdapter(private val cityList: List<City>) : RecyclerView.Adapter<CityListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListViewHolder {
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.city_list_item, parent, false)
        return CityListViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: CityListViewHolder, position: Int) {
        holder.cityName.text = cityList[position].name
    }

    override fun getItemCount(): Int {
        return cityList.size
    }
}