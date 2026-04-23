package com.example.practicaapiviajes.data.repositories

import com.example.practicaapiviajes.data.RetrofitInstance
import com.example.practicaapiviajes.data.models.Trip

class TripRepository {

    suspend fun getTripsList(): List<Trip> {
        try {
            val retrofitInstance = RetrofitInstance.api
            return retrofitInstance.listTrips()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }
    suspend fun createTrip(trip: Trip): Trip? {
        try {
            val retrofitInstance = RetrofitInstance.api
            return retrofitInstance.createTrip(trip)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}