package com.example.catchup.database

import kotlinx.coroutines.flow.Flow

class ReminderRepository(val reminderDao: ReminderDao){
    val allReminders: Flow<List<Reminder>> = reminderDao.getAllReminders()

    suspend fun insertReminder(reminder: Reminder){
        reminderDao.insert(reminder)
    }

    suspend fun deleteReminder(reminder: Reminder){
        reminderDao.delete(reminder)
    }

    suspend fun getReminderById(id: Int): Reminder {
        return reminderDao.getReminderById(id)
    }

    suspend fun updateCompletion(id: Int, completed:Boolean){
        reminderDao.updateCompletionStatus(id, completed)
    }

}
