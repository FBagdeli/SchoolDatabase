package com.farshadchalenges.schooldatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.farshadchalenges.schooldatabase.entities.Director
import com.farshadchalenges.schooldatabase.entities.School
import com.farshadchalenges.schooldatabase.entities.Student
import com.farshadchalenges.schooldatabase.entities.Subject
import com.farshadchalenges.schooldatabase.entities.relations.StudentsSubjectCrossRef
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [
        School::class,
        Director::class,
        Student::class,
        Subject::class,
        StudentsSubjectCrossRef::class
    ],
    version = 1
)
abstract class SchoolDatabase : RoomDatabase() {

    abstract val schoolDao:SchoolDao

    companion object{
        @Volatile
        private var INSTANCE:SchoolDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context:Context):SchoolDatabase{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDatabase::class.java,
                    "school_db"
                ).build().also {
                    INSTANCE=it
                }
            }
        }
    }

}