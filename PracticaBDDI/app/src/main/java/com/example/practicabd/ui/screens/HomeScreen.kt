package com.example.practicabd.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.practicabd.data.entities.Person
import com.example.practicabd.data.entities.PersonWithPhones
import com.example.practicabd.ui.viewmodels.HomeScreenViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    vm: HomeScreenViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton =
            {
                Column {
                    FloatingActionButton(
                        onClick = { vm.insertExamplePerson() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Add default person"
                        )
                    }
                    FloatingActionButton(
                        onClick = { navController.navigate(NavScreens.FORM.name) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add person"
                        )
                    }
                }
            }
    ) { innerPadding ->
        PersonList(modifier.padding(innerPadding), vm)
    }
}

@Composable
fun PersonList(modifier: Modifier, vm: HomeScreenViewModel) {
    LaunchedEffect(Unit) {
        vm.loadPeople()
    }
    val personList = vm.people.collectAsState()
    val selectedPerson = vm.selectedPerson.collectAsState()
    if (personList.value.isEmpty()) {
        Text("No hay personas insertadas", modifier = modifier.padding(16.dp))
        return
    }
    if (selectedPerson.value != null) {
        PersonDetailPopup(selectedPerson.value!!, vm)
    }
    LazyColumn(modifier = modifier) {
        items(personList.value.size) { index ->
            val person = personList.value[index]
            PersonItem(person, vm)
        }
    }
}

@Composable
fun PersonDetailPopup(person: PersonWithPhones, vm: HomeScreenViewModel) {
    AlertDialog(
        onDismissRequest = { vm.selectedPerson.value = null },
        title = { Text(text = "${person.person?.name} ${person.person?.lastName} Details") },
        text = {
            Column {
                Text("Age: ${person.person?.age}")
                Text("City: ${person.person?.city}")
                Text("Phones:")
                person.phones.forEach { phone ->
                    Text("- ${phone.number} (${if (phone.type == 0) "Mobile" else "Home"})")
                }
            }
        },
        confirmButton = {
            Button(onClick = { vm.selectedPerson.value = null }) {
                Text("Close")
            }
        }
    )
}

@Composable
fun PersonItem(person: Person, vm: HomeScreenViewModel) {
    Row(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "${person.name} ${person.lastName}, Age: ${person.age}, City: ${person.city}",
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = {
                vm.addRandomPhone(person)
            },
        ) {
            Icon(imageVector = Icons.Default.Phone, contentDescription = "Add random phone")
        }
        Button(
            onClick = {
                //show person popup with details and phones
                vm.showPersonDetails(person)
            }
        ) {
            Icon(imageVector = Icons.Default.Person, contentDescription = "View details")
        }
    }
}

