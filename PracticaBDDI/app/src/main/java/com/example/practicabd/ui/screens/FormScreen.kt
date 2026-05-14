package com.example.practicabd.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.practicabd.repositories.PersonRepository
import com.example.practicabd.ui.viewmodels.FormScreenViewModel

@Composable
fun FormScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    vm: FormScreenViewModel = hiltViewModel()

    ) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        PersonForm(
            modifier.padding(innerPadding), vm
        ) {
            navController.popBackStack()
        }
    }
}

@Composable
fun PersonForm(modifier: Modifier, vm: FormScreenViewModel, onCancelClick: () -> Unit) {
    var name by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var birthDate by rememberSaveable { mutableStateOf("") }
    val formState = vm.state.collectAsState()
    if (formState.value.isSaved) {
        onCancelClick()
        return
    }
    if (!formState.value.error.isEmpty()) {
        Toast.makeText(LocalContext.current, "Error: ${formState.value.error}", Toast.LENGTH_LONG)
            .show()
        vm.errorShown()
    }
    Column(modifier = modifier) {
        Text("Formulario de persona")
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Apellido") }
        )
        OutlinedTextField(
            value = age,
            onValueChange = { age = (it.toIntOrNull() ?: 0).toString() },
            label = { Text("Edad") }
        )
        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Ciudad") }
        )
        OutlinedTextField(
            value = birthDate,
            onValueChange = { birthDate = it },
            label = { Text("Fecha de nacimiento") }
        )
        Button(
            onClick = {
                vm.insertPerson(
                    name = name,
                    lastName = lastName,
                    age = (age.toIntOrNull() ?: 0).toString(),
                    city = city,
                    birthDate = birthDate
                )
            }
        ) {
            Text("Guardar")
        }
        Button(
            onClick = { onCancelClick() }
        ) {
            Text("Cancelar")
        }
    }
}