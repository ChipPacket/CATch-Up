package com.example.catchup.ui.UIStates

import androidx.lifecycle.ViewModel
import com.example.catchup.database.ReminderRepository
import com.example.catchup.ui.uiStates.ReminderScreenUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ReminderScreenViewModel(private val repository: ReminderRepository) : ViewModel() {
    // expose screen ui state
    private val _uiState = MutableStateFlow(ReminderScreenUIState())
    val uiState: StateFlow<ReminderScreenUIState> = _uiState.asStateFlow()

    // run a function woo woo woo
    fun getReminders(){
        _uiState.update{ currentState ->
            currentState.copy(
                //reminderItems =
            )
        }
    }
}

// https://github.com/demolaf/I-Notify-Kotlin/blob/master/app/src/main/java/com/aob/inotify/database/RemindersDatabase.kt