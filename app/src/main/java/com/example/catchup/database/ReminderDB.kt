package com.example.catchup.database

import android.content.Context
import androidx.room3.ColumnTypeConverters
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase

// ROOM DATABASE
@Database(
    entities = [Reminder::class],
    version = 1
)
@ColumnTypeConverters(Converters::class)
abstract class ReminderDatabase : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao
    companion object {
        @Volatile
        private var INSTANCE: ReminderDatabase? = null

        fun getDatabase(context: Context): ReminderDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReminderDatabase::class.java,
                    "reminder_db"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}
// ROOM DATABASE END

//val db = ReminderDatabase.getDatabase(this)