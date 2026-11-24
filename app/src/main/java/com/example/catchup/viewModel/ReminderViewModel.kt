package com.example.catchup.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.catchup.database.ReminderDatabase
import com.example.catchup.database.ReminderRepository
import com.example.catchup.database.Reminder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ReminderViewModel(application: Application) : AndroidViewModel(application){
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
        //date: String,
        timeMillis: Long,
        completed: Boolean,
        enabled: Boolean
    ){
        viewModelScope.launch{
            val newReminder = Reminder (
                name = name,
                time = time,
                //date = date,
                timeMillis = timeMillis,
                completed = completed,
                enabled = enabled
            )
            val id = repository.insertReminder(newReminder)
            /*
            val insertedEvent = newEvent.copy(id = id.toInt())
            if (hasNotification) {
                AlarmScheduler.scheduleAlarm(getApplication(), insertedEvent)
            }
             */
        }
    }

    /*
    fun editReminder(
        name: String,
        alarmTimeHour: Int,
        alarmTimeMinutes: Int,
        reminderDateString: String,
        dateMillis: Long,
        alarmTimeMillis: Long,
        completed: Boolean,
        enabled: Boolean,
        id: Int
    ){
        viewModelScope.launch{
            val oldReminder = repository.getReminderById(id)

            //if (oldReminder != null){
                /*
                    // Cancel old alarm
                    AlarmScheduler.cancelAlarm(getApplication(), oldEvent)
                 */
            //}

            //val newReminder = oldReminder.

            // val id = repository.insertReminder(newReminder)
            /*
            val insertedEvent = newEvent.copy(id = id.toInt()) <--LOVE WHEN THIS DOESNT WORK FOR NO REASON
            if (hasNotification) {
                AlarmScheduler.scheduleAlarm(getApplication(), insertedEvent)
            }
             */
        }
    }

     */

    fun deleteReminder(reminder: Reminder){
        viewModelScope.launch{
            //AlarmScheduler.cancelAlarm(getApplication(), event)
            repository.deleteReminder(reminder)
        }
    }

    fun setComplete(reminder: Reminder){
        viewModelScope.launch{
            val nextCompleted = !reminder.completed
            repository.updateCompletion(reminder.id, reminder.completed)

            //if (nextCompleted){
                //AlarmScheduler.cancelAlarm(getApplication(), event)
            //}
        }
    }
    // ##### OPERATIONS AND ACTIONS END #####
    // > ########## ROOM DATABASE INTEGRATION END ##########


    // ##### SETTING STATES #####

    /*
        ENTER CODE FOR SETTING STATES HERE
        WOO
        MORE LINES OF CODE
        SO EXCITING
     */
    // ##### SETTING STATES END #####

}