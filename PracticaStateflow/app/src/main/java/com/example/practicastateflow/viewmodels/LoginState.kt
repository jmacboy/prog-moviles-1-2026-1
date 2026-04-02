package com.example.practicastateflow.viewmodels

data class LoginState(
    var username: String = "",
    var password: String = "",
    var loginSuccess: Boolean? = null,
    var errorMessage: String? = null
)