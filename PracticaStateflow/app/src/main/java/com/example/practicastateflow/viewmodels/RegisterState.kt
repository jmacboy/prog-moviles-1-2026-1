package com.example.practicastateflow.viewmodels

data class RegisterState(
    val fullName: String = "",
    val username: String = "",
    val password: String = "",
    val showFullNameError: Boolean = false,
    val showUsernameError: Boolean = false,
    val showPasswordError: Boolean = false,

)