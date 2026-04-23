package com.example.practicaapiviajes.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaapiviajes.data.repositories.TripRepository
import com.example.practicaapiviajes.ui.states.TripListUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import kotlinx.coroutines.launch

class TripListViewModel : ViewModel() {
    private val _state: MutableStateFlow<TripListUIModel> = MutableStateFlow(
        TripListUIModel(
            emptyList()
        )
    )

    val state: StateFlow<TripListUIModel> = _state.asStateFlow()
    val repository = TripRepository()

    fun fetchTrips() = viewModelScope.launch {
        val result = repository.getTripsList()
        _state.update {
            it.copy(
                tripList = result,
                loading = false
            )
        }
    }
}