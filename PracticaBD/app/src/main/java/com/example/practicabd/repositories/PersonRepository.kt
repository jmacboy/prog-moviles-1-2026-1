package com.example.practicabd.repositories

import android.content.Context
import com.example.practicabd.data.db.AppDatabase
import com.example.practicabd.data.entities.Person

class PersonRepository(
    private val context: Context
) {
    suspend fun getAllPeople(): List<Person> {
        return AppDatabase
            .getInstance(context)
            .personDao()
            .getAllPeople()
    }

    suspend fun insertPerson(person: Person) {
        AppDatabase
            .getInstance(context)
            .personDao()
            .insertPerson(person)
    }
}