package com.example.practicabd.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.practicabd.data.daos.PersonDao
import com.example.practicabd.data.daos.PhoneDao
import com.example.practicabd.data.entities.Person
import com.example.practicabd.data.entities.Phone
import com.example.practicabd.data.migrations.Migration1to2

@Database(entities = [Person::class, Phone::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun phoneDao(): PhoneDao

    companion object {
        const val DB_NAME = "prueba-db"
    }
}