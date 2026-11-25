package com.example.catchup.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.catchup.database.ReminderDatabase
import com.example.catchup.database.ReminderRepository
import com.example.catchup.database.Reminder
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.catchup.notifications.NotificationAlarmScheduler
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

class ReminderViewModel(
    application: Application,
    context: Context
) : AndroidViewModel(application){
    // > ########## ROOM DATABASE INTEGRATION ##########
    // ##### INITIALISING REPOSITORY #####
    private val repository: ReminderRepository // repo
    val allReminders: StateFlow<List<Reminder>> // repo db stream

    init {
        val db = ReminderDatabase.getDatabase(application)
        repository = ReminderRepository(db.reminderDao())

        allReminders = repository.allReminders.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    // ##### OPERATIONS AND ACTIONS #####
    suspend fun getReminderById(id: Int) : Reminder? {
        return repository.getReminderById(id)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun addReminder(
        name: String,
        time: String,
        timeMillis: Long,
        completed: Boolean,
        enabled: Boolean
    ){
        viewModelScope.launch{
            val newReminder = Reminder(
                name = name,
                time = time,
                timeMillis = timeMillis,
                completed = completed,
                enabled = enabled
            )

            val generatedId = repository.insertReminder(newReminder)

            notificationAlarmScheduler.schedule(
                newReminder.copy(
                    id = generatedId.toInt()
                )
            )
        }
    }

    fun deleteReminder(id: Int){
        viewModelScope.launch{
            //notificationAlarmScheduler.cancel(getReminderById(id))
           // repository.deleteReminder(getReminderById(id))
        }
    }

    fun setComplete(reminder: Reminder){
        viewModelScope.launch{
            val nextCompleted = !reminder.completed
            repository.updateCompletion(reminder.id, reminder.completed)
            //notificationAlarmScheduler.cancel(reminder)
        }
    }

    fun deleteAllReminders() {
        viewModelScope.launch {

            // Get all reminders currently stored
            val reminders = allReminders.value

            // Cancel all scheduled alarms
            reminders.forEach { reminder ->
                notificationAlarmScheduler.cancel(reminder)
            }

            // Delete everything from Room
            repository.deleteAllReminders()
        }
    }

    fun deleteReminderbyId(id: Int) {
        viewModelScope.launch {

            val reminder = repository.getReminderById(id)

            notificationAlarmScheduler.cancel(reminder)

            repository.deleteReminder(reminder)
        }
    }
    // ##### OPERATIONS AND ACTIONS END #####
    // > ########## ROOM DATABASE INTEGRATION END ##########

    // > ##### NOTIFICATION HANDLER  #####
    val notificationAlarmScheduler by lazy {
        NotificationAlarmScheduler(context)
    }
    // > ##### NOTIFICATION HANDLER END  #####


    // ##### SETTING STATES #####
    /*
        ENTER CODE FOR SETTING STATES HERE
     */
    // ##### SETTING STATES END #####

}