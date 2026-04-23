package com.example.practicaapiviajes.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaapiviajes.data.models.Trip
import com.example.practicaapiviajes.data.repositories.TripRepository
import com.example.practicaapiviajes.ui.states.TripFormUIModel
import com.example.practicaapiviajes.ui.states.TripListUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TripFormViewModel : ViewModel() {
    private val _state: MutableStateFlow<TripFormUIModel> = MutableStateFlow(
        TripFormUIModel()
    )

    val state: StateFlow<TripFormUIModel> = _state.asStateFlow()
    val repository = TripRepository()
    fun saveTrip(name: String, country: String) = viewModelScope.launch {
        repository.createTrip(
            Trip(
                name = name,
                country = country,
                username = "android"
            )
        )
        _state.update {
            it.copy(
              tripSavedCorrectly = true
            )
        }
    }
}