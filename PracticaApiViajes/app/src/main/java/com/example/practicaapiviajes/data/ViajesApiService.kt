package com.example.practicaapiviajes.data

import com.example.practicaapiviajes.data.models.Trip
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ViajesApiService {
    @GET("trips")
    suspend fun listTrips(): List<Trip>

    @POST("trips")
    suspend fun createTrip(@Body trip: Trip): Trip
}