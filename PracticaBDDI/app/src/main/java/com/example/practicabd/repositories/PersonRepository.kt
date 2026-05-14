package com.example.practicabd.repositories

import android.content.Context
import com.example.practicabd.data.db.AppDatabase
import com.example.practicabd.data.entities.Person
import com.example.practicabd.data.entities.PersonWithPhones
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val db: AppDatabase
) {
    suspend fun getAllPeople(): List<Person> {
        return db
            .personDao()
            .getAllPeople()
    }

    suspend fun insertPerson(person: Person) {
        db
            .personDao()
            .insertPerson(person)
    }

    suspend fun getPersonById(id: Int): PersonWithPhones {
        return db
            .personDao()
            .getPersonWithPhones(id)
    }
}