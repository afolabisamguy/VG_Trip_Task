package com.alphatech.vg_trip_task.view

import kotlinx.serialization.Serializable

@Serializable
data class MyTrip(
    val city: String,
    val startDate: String,
    val endDate: String,
    val tripName: String,
    val travelStyle: String,
    val tripDescription: String,
    val hotels: List<String>,   // List of hotel names
    val flights: List<String>,  // List of flight details
    val activities: List<String> // List of activities
)

