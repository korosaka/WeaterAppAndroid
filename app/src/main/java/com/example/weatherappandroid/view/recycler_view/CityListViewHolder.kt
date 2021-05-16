package com.example.weatherappandroid.view.recycler_view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappandroid.R

class CityListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var cityName: TextView = itemView.findViewById(R.id.city_name)

}