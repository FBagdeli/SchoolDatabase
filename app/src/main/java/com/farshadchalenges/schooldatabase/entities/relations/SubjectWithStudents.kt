package com.farshadchalenges.schooldatabase.entities.relations

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.farshadchalenges.schooldatabase.entities.Student
import com.farshadchalenges.schooldatabase.entities.Subject

@Entity
data class SubjectWithStudents(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subjectName",
        entityColumn = "studentName",
        associateBy = Junction(StudentsSubjectCrossRef::class)
    )
    val student: List<Student>
)
