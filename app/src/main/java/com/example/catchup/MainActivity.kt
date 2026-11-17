package com.example.catchup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.room3.Room
import androidx.sqlite.driver.AndroidSQLiteDriver
import com.example.catchup.database.Reminder
import com.example.catchup.database.ReminderDatabase
import com.example.catchup.ui.screens.BottomNavBar
import com.example.catchup.ui.theme.CATchUpTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            ReminderDatabase::class.java,
            "reminder_db"
        )
            .setDriver(AndroidSQLiteDriver())
            .build()
        val reminderDao = db.reminderDao()

        //val rem1 = Reminder(0, "Wash Hair", "20:00", "Tomorrow")
        //val rem2 = Reminder(1, "Walk Dog", "15:00", "Tomorrow")
        //val rem3 = Reminder(2, "Kill John Lennon", "12:00", "Tomorrow")

        //reminderDao.insert(rem1)
        //reminderDao.insert(rem2)
        //reminderDao.insert(rem3)

        // https://developer.android.com/training/data-storage/room
        // https://developer.android.com/training/data-storage/room/referencing-data

        // gotta pass the instance of db n reminder dao through fucking everything i think
        // or have functions that run it from here??? bro

        // just pass in the DAO


        setContent {
            CATchUpTheme {
                BottomNavBar() // run UI
            }
        }
    }
}


