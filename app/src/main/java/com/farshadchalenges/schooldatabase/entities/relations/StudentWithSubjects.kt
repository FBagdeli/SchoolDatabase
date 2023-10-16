package com.farshadchalenges.schooldatabase.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.farshadchalenges.schooldatabase.entities.Student
import com.farshadchalenges.schooldatabase.entities.Subject

data class StudentWithSubjects(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentName",
        entityColumn = "subjectName",
        associateBy = Junction(StudentsSubjectCrossRef::class)
    )
    val subject: List<Subject>
)
