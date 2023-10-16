package com.farshadchalenges.schooldatabase.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.farshadchalenges.schooldatabase.entities.School
import com.farshadchalenges.schooldatabase.entities.Student

data class SchoolWithStudents(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val students:List<Student>
)