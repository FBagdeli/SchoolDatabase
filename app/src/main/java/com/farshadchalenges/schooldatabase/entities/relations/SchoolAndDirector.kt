package com.farshadchalenges.schooldatabase.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.farshadchalenges.schooldatabase.entities.Director
import com.farshadchalenges.schooldatabase.entities.School

data class SchoolAndDirector(
    @Embedded val school : School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val director: Director
)
