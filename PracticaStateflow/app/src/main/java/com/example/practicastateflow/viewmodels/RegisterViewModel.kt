package com.example.practicastateflow.viewmodels

import androidx.lifecycle.ViewModel
import com.example.practicastateflow.User
import com.example.practicastateflow.repositories.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    fun updateUsername(newUsername: String) {
        _state.value = _state.value.copy(username = newUsername)
    }

    fun updatePassword(newPassword: String) {
        _state.value = _state.value.copy(password = newPassword)
    }

    fun updateFullName(fullName: String) {
        _state.value = _state.value.copy(fullName = fullName)
    }

    fun register() {
        var hasError = false
        _state.value = _state.value.copy(
            showFullNameError = state.value.fullName.isEmpty()
        )
        hasError = hasError || state.value.fullName.isEmpty()

        _state.value = _state.value.copy(
            showUsernameError = state.value.username.isEmpty()
        )
        hasError = hasError || state.value.username.isEmpty()


        _state.value = _state.value.copy(
            showPasswordError = state.value.password.isEmpty()
        )
        hasError = hasError || state.value.password.isEmpty()

        if (hasError) return

        _state.value = _state.value.copy(
            showFullNameError = false,
            showUsernameError = false,
            showPasswordError = false,
        )
        UserRepository.registerUser(
            fullName = state.value.fullName,
            username = state.value.username,
            password = state.value.password
        )
    }

}