package com.example.klivvrandroidtask.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klivvrandroidtask.data.model.City
import com.example.klivvrandroidtask.databinding.CityItemBinding

class CityAdapter(private val onItemClick: (City) -> Unit) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private val cities = mutableListOf<City>()

    class CityViewHolder(private val binding: CityItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City, onItemClick: (City) -> Unit) {
            binding.cityName.text = "${city.name}, ${city.country}"
            binding.cityCoordinates.text = "Lon: ${city.coord.lon}, Lat: ${city.coord.lat}"
            binding.root.setOnClickListener { onItemClick(city) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cities[position], onItemClick)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    fun updateCities(newCities: List<City>) {
        cities.clear()
        cities.addAll(newCities)
        notifyDataSetChanged()
    }
}