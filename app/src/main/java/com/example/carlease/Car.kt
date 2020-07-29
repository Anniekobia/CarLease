package com.example.carlease


data class Car(
    val carModel: String,
    val carColor: String,
    val carImage: Int,
    val numOfDoors: Int,
    val numOfseaters: Int,
    val rating: String,
    val isAutomatic: Boolean,
    val hasAirConditioner: Boolean,
    val chargePerDay: Int,
    val state: String,
    val deals: ArrayList<Deal>
)
