package com.example.practicabd.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.practicabd.data.daos.PersonDao
import com.example.practicabd.data.entities.Person

@Database(entities = [Person::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object{
        const val DB_NAME = "prueba-db"
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = databaseBuilder(
                    context,
                    AppDatabase::class.java, DB_NAME
                )
                    .build()
            }
            return INSTANCE!!
        }

    }
}