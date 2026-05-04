package com.example.practicabd.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicabd.repositories.PersonRepository
import com.example.practicabd.ui.theme.PracticaBDTheme
import com.example.practicabd.ui.viewmodels.HomeScreenViewModel


@Composable
fun HomeScreen(modifier: Modifier = Modifier, vm: HomeScreenViewModel = HomeScreenViewModel(
    PersonRepository(LocalContext.current)
)) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton =
            {
                FloatingActionButton(
                    onClick = { vm.insertExamplePerson() }
                ) {
                    Text("+")
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
    if (personList.value.isEmpty()) {
        Text("No hay personas insertadas", modifier = modifier.padding(16.dp))
        return
    }
    LazyColumn(modifier = modifier) {
        items(personList.value.size) { index ->
            val person = personList.value[index]
            Text(text = "ID: ${person.id}, Name: ${person.name}, Age: ${person.age}", modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PracticaBDTheme {
        HomeScreen()
    }
}