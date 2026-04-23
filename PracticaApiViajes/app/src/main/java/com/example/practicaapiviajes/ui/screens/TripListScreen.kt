package com.example.practicaapiviajes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavHostController
import com.example.practicaapiviajes.data.models.Trip
import com.example.practicaapiviajes.ui.NavScreens
import com.example.practicaapiviajes.ui.viewmodels.TripListViewModel

@Composable
fun TripListScreen(
    modifier: Modifier = Modifier,
    vm: TripListViewModel = TripListViewModel(),
    navController: NavHostController
) {
    // Se llama a fetchTrips cada vez que la pantalla de TripList se muestra
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        vm.fetchTrips()
    }
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavScreens.TRIP_FORM.name)
                }
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Agregar viaje",
                )
            }
        }
    ) { innerPadding ->
        TripList(modifier = Modifier.padding(innerPadding), vm = vm)
    }
}

@Composable
fun TripList(
    modifier: Modifier = Modifier,
    vm: TripListViewModel = TripListViewModel()
) {
    val tripListState by vm.state.collectAsState()
    if(tripListState.loading) {
        CircularProgressIndicator()
        return
    }
    LazyColumn(modifier = modifier) {
        items(tripListState.tripList) {
            TripItem(item = it)
            HorizontalDivider()
        }
    }
}

@Composable
fun TripItem(item: Trip, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(8.dp)) {
        Column(Modifier.weight(1f)) {
            Text(text = item.name, fontSize = 20.sp)
            Text(text = item.country)
        }
        Button(
            onClick = {}
        ) {
            Text(text = "Editar")
        }
    }
}