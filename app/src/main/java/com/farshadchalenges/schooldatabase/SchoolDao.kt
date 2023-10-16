package com.farshadchalenges.schooldatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.farshadchalenges.schooldatabase.entities.Director
import com.farshadchalenges.schooldatabase.entities.School
import com.farshadchalenges.schooldatabase.entities.Student
import com.farshadchalenges.schooldatabase.entities.Subject
import com.farshadchalenges.schooldatabase.entities.relations.SchoolAndDirector
import com.farshadchalenges.schooldatabase.entities.relations.SchoolWithStudents
import com.farshadchalenges.schooldatabase.entities.relations.StudentWithSubjects
import com.farshadchalenges.schooldatabase.entities.relations.StudentsSubjectCrossRef
import com.farshadchalenges.schooldatabase.entities.relations.SubjectWithStudents
import kotlinx.coroutines.flow.Flow

@Dao
interface SchoolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director: Director)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentSubjectCrossRef(crossRef: StudentsSubjectCrossRef)

    @Transaction
    @Query("SELECT * FROM school WHERE  schoolName = :schoolName ")
    suspend fun getAllSchoolsAndDirectorWithSchoolNames(schoolName:String):List<SchoolAndDirector>

    @Transaction
    @Query("SELECT * FROM school WHERE schoolName=:schoolName")
    suspend fun getSchoolWithStudents(schoolName:String):List<SchoolWithStudents>

    @Transaction
    @Query("SELECT * FROM subject WHERE  subjectName = :subjectName ")
    suspend fun getStudentsOfSubject(subjectName :String):List<SubjectWithStudents>

    @Transaction
    @Query("SELECT * FROM student WHERE  studentName = :studentName ")
    suspend fun getSubjectsOfStudent(studentName:String):List<StudentWithSubjects>


}