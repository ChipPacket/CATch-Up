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
    suspend fun getReminderById(id: Int) {
        return repository.getReminderById(id)
    }

    fun addReminder(
        name: String,
        time: String,
        timeMillis: Long,
        completed: Boolean,
        enabled: Boolean
    ){
        viewModelScope.launch{
            val newReminder = Reminder (
                name = name,
                time = time,
                timeMillis = timeMillis,
                completed = completed,
                enabled = enabled
            )
            //val id = repository.insertReminder(newReminder)
            repository.insertReminder(newReminder)
            notificationAlarmScheduler.schedule(newReminder)
        }
    }

    fun deleteReminder(reminder: Reminder){
        viewModelScope.launch{
            notificationAlarmScheduler.cancel(reminder)
            repository.deleteReminder(reminder)
        }
    }

    fun setComplete(reminder: Reminder){
        viewModelScope.launch{
            val nextCompleted = !reminder.completed
            repository.updateCompletion(reminder.id, reminder.completed)
            //notificationAlarmScheduler.cancel(reminder)
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