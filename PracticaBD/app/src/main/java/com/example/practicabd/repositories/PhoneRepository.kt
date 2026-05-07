package com.example.practicabd.repositories

import android.content.Context
import com.example.practicabd.data.db.AppDatabase
import com.example.practicabd.data.entities.Person
import com.example.practicabd.data.entities.Phone

class PhoneRepository(
    private val context: Context
) {
    suspend fun insertPhone(phone: Phone) {
        AppDatabase
            .getInstance(context)
            .phoneDao()
            .insertPhone(phone)
    }
}