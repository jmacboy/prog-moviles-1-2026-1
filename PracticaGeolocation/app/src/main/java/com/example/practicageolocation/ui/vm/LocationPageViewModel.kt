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

    fun setLocation(
        latitude: String,
        longitude: String
    ) {
        _state.update {
            it.copy(
                latitude = latitude,
                longitude = longitude
            )
        }
    }
}