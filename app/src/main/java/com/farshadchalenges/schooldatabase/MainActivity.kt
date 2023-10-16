package com.farshadchalenges.schooldatabase

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.farshadchalenges.schooldatabase.entities.Director
import com.farshadchalenges.schooldatabase.entities.School
import com.farshadchalenges.schooldatabase.entities.Student
import com.farshadchalenges.schooldatabase.entities.Subject
import com.farshadchalenges.schooldatabase.entities.relations.StudentsSubjectCrossRef
import com.farshadchalenges.schooldatabase.ui.theme.SchoolDatabaseTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolDatabaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val dao = SchoolDatabase.getInstance(this).schoolDao

                    val directors = listOf(
                        Director("Mike","Harvard School"),
                        Director("Azam","Boston School")
                    )
                    val schools = listOf(
                        School("Harvard School"),
                        School("Boston School")
                    )
                    val subjects = listOf(
                        Subject("Mathematics"),
                        Subject("Physics"),
                        Subject("Art")
                    )
                    val students = listOf(
                        Student("Farshad",7,"Harvard School"),
                        Student("Azam",5,"Harvard School"),
                        Student("javad",10,"Boston School"),
                        Student("Shaian",1,"Boston School")
                    )
                    val studentsSubjectRelation = listOf(
                        StudentsSubjectCrossRef("Farshad","Mathematics"),
                        StudentsSubjectCrossRef("Farshad","Physics"),
                        StudentsSubjectCrossRef("Farshad","Art"),
                        StudentsSubjectCrossRef("Azam","Physics"),
                        StudentsSubjectCrossRef("javad","Mathematics"),
                        StudentsSubjectCrossRef("Shaian","Art"),
                    )
                    lifecycleScope.launch {
                        directors.forEach{ dao.insertDirector(it)}
                        schools.forEach { dao.insertSchool(it) }
                        subjects.forEach { dao.insertSubject(it) }
                        students.forEach { dao.insertStudent(it) }
                        studentsSubjectRelation.forEach { dao.insertStudentSubjectCrossRef(it) }

                        val schoolWithStudents=dao.getSchoolWithStudents("Harvard School")
                        schoolWithStudents.first().students
                    }
                }
            }
        }
    }
}
