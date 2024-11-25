package com.alphatech.vg_trip_task.viewModel

import com.alphatech.vg_trip_task.model.TripRepository
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphatech.vg_trip_task.view.MyTrip
import com.alphatech.vg_trip_task.model.createTrip
import com.alphatech.vg_trip_task.model.fetchTrips
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TripViewModel: ViewModel() {

    private val _tripName = MutableStateFlow("")
    private val _tripDescription = MutableStateFlow("")
    private val _travelStyle = MutableStateFlow("")
    val tripName: StateFlow<String> get() = _tripName
    val tripDescription: StateFlow<String> get() = _tripDescription
    val travelStyle: StateFlow<String> get() = _travelStyle

    val trip = TripRepository.trip

    fun updateCity(newCity: String) {
        TripRepository.trip.value = trip.value.copy(city = newCity)
    }

    fun updateStartDate(newStartDate: String) {
        TripRepository.trip.value = trip.value.copy(startDate = newStartDate)
    }

    fun updateEndDate(newEndDate: String) {
        TripRepository.trip.value = trip.value.copy(endDate = newEndDate)
    }

    fun updateTravelStyle(newTravelStyle: String) {
        TripRepository.trip.value = trip.value.copy(travelStyle = newTravelStyle)
    }

    fun updateTripName(newTripName: String) {
        _tripName.value = newTripName
        TripRepository.trip.value = trip.value.copy(tripName = newTripName)
    }

    fun updateTripDescription(newTripDescription: String) {
        _tripDescription.value = newTripDescription
        TripRepository.trip.value = trip.value.copy(tripDescription = newTripDescription)
    }

    fun updateHotels(newHotels: List<String>) {
        TripRepository.trip.value = trip.value.copy(hotels = newHotels)
    }

    fun updateFlights(newFlights: List<String>) {
        TripRepository.trip.value = trip.value.copy(flights = newFlights)
    }

    fun updateActivities(newActivities: List<String>) {
        TripRepository.trip.value = trip.value.copy(activities = newActivities)
    }

    fun callCreate(context: Context, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                
                val response = createTrip(TripRepository.trip.value)
                if (response.status.isSuccess()) {
                    val successMessage = response.bodyAsText()
                    println(successMessage)
                    Toast.makeText(context, "Success: $successMessage", Toast.LENGTH_LONG).show()
                    onComplete(true)
                } else {
                    val errorMessage = response.bodyAsText()
                    println(errorMessage)
                    Toast.makeText(context, "Error: $errorMessage", Toast.LENGTH_LONG).show()
                    onComplete(false)
                }
            } catch (e: Exception) {
                println(e)
                Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()

                onComplete(false)
            }
        }
    }

    private val _collectedTrips = MutableStateFlow<List<MyTrip>>(emptyList())
    val collectedTrips: StateFlow<List<MyTrip>> get() = _collectedTrips

    fun callReceive(){
        viewModelScope.launch {
            val fetchedTrips = fetchTrips()
            
            _collectedTrips.value = fetchedTrips
        }
    }

}