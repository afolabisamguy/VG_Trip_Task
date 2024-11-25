package com.alphatech.vg_trip_task.model

import androidx.compose.runtime.mutableStateOf
import com.alphatech.vg_trip_task.view.MyTrip

object TripRepository {
    var trip = mutableStateOf(
        MyTrip(
            city = "",
            startDate = "",
            endDate = "",
            tripName = "",
            travelStyle = "",
            tripDescription = "",
            hotels = listOf(),
            flights = listOf(),
            activities = listOf(),
        )
    )

    fun resetTrip() {
        trip.value =  MyTrip(
            city = "",
            startDate = "",
            endDate = "",
            tripName = "",
            travelStyle = "",
            tripDescription = "",
            hotels = listOf(),
            flights = listOf(),
            activities = listOf(),
        )
    }
}