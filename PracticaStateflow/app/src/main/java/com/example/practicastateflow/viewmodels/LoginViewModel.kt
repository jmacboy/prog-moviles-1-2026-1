package com.example.practicastateflow.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState(
        username = "admin",
        password = "admin"
    ))
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun updateUsername(newUsername: String) {
        _state.update {
            it.copy(username = newUsername)
        }
    }

    fun updatePassword(newPassword: String) {
        _state.update {
            it.copy(password = newPassword)
        }
    }

    fun login() {
        if (_state.value.username == "admin" && _state.value.password == "admin") {
            _state.update {
                it.copy(
                    loginSuccess = true,
                    errorMessage = null
                )
            }
        } else {
            _state.update {
                it.copy(
                    loginSuccess = false,
                    errorMessage = "Usuario o contraseña incorrectos"
                )
            }
        }
    }

    fun errorShown() {
        _state.update {
            it.copy(errorMessage = null)
        }
    }

    fun resetLoginSuccess() {
        _state.update {
            it.copy(loginSuccess = null)
        }
    }
}