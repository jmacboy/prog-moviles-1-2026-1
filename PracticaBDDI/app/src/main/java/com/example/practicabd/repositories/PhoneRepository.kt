package com.example.practicabd.repositories

import com.example.practicabd.data.db.AppDatabase
import com.example.practicabd.data.entities.Phone
import javax.inject.Inject

class PhoneRepository @Inject constructor(
    private val db: AppDatabase
) {
    suspend fun insertPhone(phone: Phone) {
        db
            .phoneDao()
            .insertPhone(phone)
    }
}