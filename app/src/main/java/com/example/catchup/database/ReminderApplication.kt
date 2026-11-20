package com.example.catchup.database

import android.app.Application

class ReminderApplication: Application(){
    private val db by lazy{
        ReminderDatabase.getDatabase(this)
    }

    val repository by lazy {
        ReminderRepository(db.reminderDao())
    }

}