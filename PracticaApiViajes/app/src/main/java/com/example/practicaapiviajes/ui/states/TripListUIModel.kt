package com.example.practicaapiviajes.ui.states

import com.example.practicaapiviajes.data.models.Trip

data class TripListUIModel(
    val tripList: List<Trip>,
    val loading: Boolean = true,
)