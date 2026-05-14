package com.example.practicabd.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class PersonWithPhones(
    @Embedded
    var person: Person? = null,
    @Relation(
        parentColumn = "id",
        entityColumn = "personId"
    )
    var phones: List<Phone> = emptyList()
)