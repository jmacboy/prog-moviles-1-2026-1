package com.example.practicabd.ui.viewmodels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicabd.data.entities.Person
import com.example.practicabd.data.entities.PersonWithPhones
import com.example.practicabd.data.entities.Phone
import com.example.practicabd.repositories.PersonRepository
import com.example.practicabd.repositories.PhoneRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val repo: PersonRepository,
    private val phoneRepo: PhoneRepository
) : ViewModel() {
    var people = MutableStateFlow<List<Person>>(emptyList())
    var selectedPerson = MutableStateFlow<PersonWithPhones?>(null)

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
    fun addRandomPhone(person: Person) = viewModelScope.launch {
        phoneRepo.insertPhone(
            Phone(
                number = generateRandomPhoneNumber(),
                type = 0,
                personId = person.id
            )
        )
    }

    private fun generateRandomPhoneNumber(): String {
        val areaCode = (100..999).random()
        val centralOfficeCode = (100..999).random()
        val lineNumber = (1000..9999).random()
        return "$areaCode-$centralOfficeCode-$lineNumber"
    }

    fun showPersonDetails(person: Person) = viewModelScope.launch {
        selectedPerson.value = repo.getPersonById(person.id)
    }
}