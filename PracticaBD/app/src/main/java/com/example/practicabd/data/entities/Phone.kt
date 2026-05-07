package com.example.practicabd.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Person::class,
            parentColumns = ["id"],
            childColumns = ["personId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.NO_ACTION
        )
    ],
)
data class Phone(
    var number: String,
    var personId: Int,
    var type: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}