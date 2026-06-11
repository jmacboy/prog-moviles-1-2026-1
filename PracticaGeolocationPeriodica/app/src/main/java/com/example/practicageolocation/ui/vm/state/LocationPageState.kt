package com.example.practicageolocation.ui.vm.state

import com.google.android.gms.maps.model.LatLng

data class LocationPageState(
    val lastLocation: LatLng? = null,
    val allLocations: List<LatLng> = arrayListOf(),
    val requestLocationUpdates: Boolean = false
) 