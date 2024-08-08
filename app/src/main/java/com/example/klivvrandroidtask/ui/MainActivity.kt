package com.example.klivvrandroidtask.ui

import CityViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klivvrandroidtask.R
import com.example.klivvrandroidtask.data.model.City
import com.example.klivvrandroidtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[CityViewModel::class.java]
    }
    private val adapter by lazy {
        CityAdapter {
            startActivity(Intent(this, MapsActivity::class.java).apply {
                putExtra("lat", it.coord.lat)
                putExtra("lon", it.coord.lon)
            })
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.citiesRv.adapter = adapter
        binding.citiesRv.layoutManager = LinearLayoutManager(this)
        viewModel.cities.observe(this) { listOfCities ->
            adapter.updateCities(listOfCities)
        }
        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.filterCities(text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}