package com.example.practicabd.ui.viewmodels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicabd.data.entities.Person
import com.example.practicabd.repositories.PersonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val repo: PersonRepository
) : ViewModel() {
    var people = MutableStateFlow<List<Person>>(emptyList())

    fun loadPeople() = viewModelScope.launch {
        people.value = repo.getAllPeople().toMutableStateList()
    }


    fun insertExamplePerson() = viewModelScope.launch {
        repo.insertPerson(
            Person(
                name = "John",
                lastName = "Doe",
                age = 30,
                city = "New York",
                birthDate = "1990-01-01"
            )
        )
        loadPeople()
    }
}