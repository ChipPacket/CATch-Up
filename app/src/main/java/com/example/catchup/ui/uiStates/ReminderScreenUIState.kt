package com.example.catchup.ui.uiStates

data class ReminderScreenUIState (
    val reminderItems: List<ReminderItemUIState> = listOf()
)

data class ReminderItemUIState(
    val description: String,
    val time: String,
    val enabled: Boolean = false,
    val completed: Boolean = false
)