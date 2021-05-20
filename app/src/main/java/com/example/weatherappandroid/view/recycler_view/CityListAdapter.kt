package com.example.weatherappandroid.view.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappandroid.databinding.CityListItemBinding
import com.example.weatherappandroid.viewModel.CityListViewModel

class CityListAdapter(
    private val viewModel: CityListViewModel,
    private val parentLifecycleOwner: LifecycleOwner
) :
    RecyclerView.Adapter<CityListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CityListItemBinding.inflate(layoutInflater, parent, false)
        return CityListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityListViewHolder, position: Int) {
        holder.binding.city = viewModel.getCity(position)
        holder.binding.viewModel = viewModel
        holder.binding.lifecycleOwner = parentLifecycleOwner

        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return viewModel.getFilteredCityCount()
    }
}