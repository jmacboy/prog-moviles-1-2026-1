package com.example.practicastateflow.repositories

import com.example.practicastateflow.User

object UserRepository {
    private val users = mutableListOf<User>()

    fun registerUser(fullName: String, username: String, password: String) {
        users.add(User(fullName.trim(), username.trim(), password.trim()))
    }
    fun login(username: String, password: String): Boolean {
        return users.any { it.username == username.trim() && it.password == password.trim() }
    }
}