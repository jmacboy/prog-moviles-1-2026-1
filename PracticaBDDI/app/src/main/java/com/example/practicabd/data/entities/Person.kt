package com.example.practicabd.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    var name: String,
    var lastName: String,
    var age: Int,
    var city: String,
    var birthDate: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}