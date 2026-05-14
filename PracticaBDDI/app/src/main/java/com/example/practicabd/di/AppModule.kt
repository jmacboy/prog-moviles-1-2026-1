package com.example.practicabd.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.example.practicabd.data.db.AppDatabase
import com.example.practicabd.data.db.AppDatabase.Companion.DB_NAME
import com.example.practicabd.data.migrations.Migration1to2
import com.example.practicabd.repositories.PersonRepository
import com.example.practicabd.repositories.PhoneRepository
import com.example.practicabd.ui.viewmodels.FormScreenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePersonRepository(db: AppDatabase): PersonRepository {
        return PersonRepository(db)
    }

    @Provides
    @Singleton
    fun providePhoneRepository(db: AppDatabase): PhoneRepository {
        return PhoneRepository(db)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return databaseBuilder(
            context,
            AppDatabase::class.java, DB_NAME
        ).addMigrations(
            Migration1to2()
        )
            .build()
    }
}