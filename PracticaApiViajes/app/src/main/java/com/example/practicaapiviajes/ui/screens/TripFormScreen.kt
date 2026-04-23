package com.example.practicaapiviajes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.practicaapiviajes.ui.viewmodels.TripFormViewModel

@Composable
fun TripFormScreen(
    modifier: Modifier = Modifier,
    vm: TripFormViewModel = TripFormViewModel(),
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Text(
                text = "Formulario de Viaje",
                modifier = Modifier.padding(16.dp)
            )
        }
    ) { innerPadding ->
        TripForm(
            modifier = Modifier.padding(innerPadding), vm = vm, onCancelClick = {
                navController.popBackStack()
            },
            onSaveComplete = {
                navController.popBackStack()
            }
        )
    }
}

@Composable
fun TripForm(
    modifier: Modifier,
    vm: TripFormViewModel,
    onCancelClick: () -> Unit,
    onSaveComplete: () -> Unit
) {
    val formState by vm.state.collectAsState()
    if (formState.tripSavedCorrectly) {
        onSaveComplete()
        return
    }


    Column(modifier = modifier) {
        var name by remember { mutableStateOf("") }
        var country by remember { mutableStateOf("") }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            value = country,
            onValueChange = { country = it },
            label = { Text("País") }
        )
        Button(
            onClick = {
                vm.saveTrip(
                    name = name,
                    country = country
                )
            }
        ) { Text("Guardar") }
        Button(
            onClick =
                onCancelClick

        ) { Text("Cancelar") }
    }
}