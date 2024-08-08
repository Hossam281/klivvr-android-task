package com.example.klivvrandroidtask.data.model

data class City(
    val country: String,
    val name: String,
    val _id: Long,
    val coord: Coordinates
)

data class Coordinates(
    val lon: Double,
    val lat: Double
)
