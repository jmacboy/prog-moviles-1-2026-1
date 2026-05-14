package com.example.practicabd.data.daos

import androidx.room.Dao
import androidx.room.Insert
import com.example.practicabd.data.entities.Phone

@Dao
interface PhoneDao {
    @Insert()
    suspend fun insertPhone(phone: Phone)
}