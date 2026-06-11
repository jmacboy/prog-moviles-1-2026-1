package com.example.practicageolocation.ui.vm

import androidx.lifecycle.ViewModel
import com.example.practicageolocation.ui.vm.state.LocationPageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LocationPageViewModel : ViewModel() {


    private val _state = MutableStateFlow(
        LocationPageState()
    )
    val state: StateFlow<LocationPageState> = _state.asStateFlow()

    fun startRequestingLocationUpdates() {
        _state.update {
            it.copy(
                requestLocationUpdates = true
            )
        }
    }
    fun stopRequestingLocationUpdates() {
        _state.update {
            it.copy(
                requestLocationUpdates = false
            )
        }
    }

    fun locationAcquired(latitude: Double, longitude: Double) {
        _state.update {
            val newLocation = com.google.android.gms.maps.model.LatLng(latitude, longitude)
            it.copy(
                lastLocation = newLocation,
                allLocations = it.allLocations + newLocation
            )
        }
    }
}