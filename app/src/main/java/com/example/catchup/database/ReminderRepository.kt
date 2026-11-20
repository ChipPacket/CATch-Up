package com.example.catchup.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class ReminderRepository(val reminderDao: ReminderDao){
    // get all reminders
    val allReminders: Flow<List<Reminder>> = reminderDao.getAllReminders()

}
