package com.example.practicabd.repositories

import android.content.Context
import com.example.practicabd.data.db.AppDatabase
import com.example.practicabd.data.entities.Person
import com.example.practicabd.data.entities.PersonWithPhones

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

    suspend fun getPersonById(id: Int): PersonWithPhones {
        return AppDatabase
            .getInstance(context)
            .personDao()
            .getPersonWithPhones(id)
    }
}