package com.example.practicabd.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicabd.data.entities.Person
import com.example.practicabd.repositories.PersonRepository
import com.example.practicabd.ui.states.PersonFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FormScreenViewModel(
    private val repo: PersonRepository
) : ViewModel() {

    private val _state = MutableStateFlow(
        PersonFormState()
    )
    val state: StateFlow<PersonFormState> = _state.asStateFlow()


    fun insertPerson(
        name: String,
        lastName: String,
        age: String,
        city: String,
        birthDate: String
    ) = viewModelScope.launch {
        try {
            repo.insertPerson(
                Person(
                    name = name,
                    lastName = lastName,
                    age = age.toIntOrNull() ?: 0,
                    city = city,
                    birthDate = birthDate
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            _state.update {
                it.copy(
                    error = "Error al guardar persona"
                )
            }
            return@launch
        }
        _state.update {
            it.copy(
                isSaved = true
            )
        }
    }

    fun errorShown() {
        _state.update {
            it.copy(
                error = ""
            )
        }
    }
}