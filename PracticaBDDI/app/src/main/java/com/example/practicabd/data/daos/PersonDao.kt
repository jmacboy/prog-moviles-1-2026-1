package com.example.practicabd.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.practicabd.data.entities.Person
import com.example.practicabd.data.entities.PersonWithPhones

@Dao
interface PersonDao {
    @Query("SELECT * FROM Person")
    suspend fun getAllPeople(): List<Person>

    @Insert()
    suspend fun insertPerson(person: Person)

    @Query("SELECT * FROM Person p WHERE p.id = :personId")
    suspend fun getPersonWithPhones(personId: Int): PersonWithPhones
}