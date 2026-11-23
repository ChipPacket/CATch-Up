package com.example.catchup.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.catchup.viewModel.ReminderViewModel

@Composable
fun RemindersScreen(
    viewModel: ReminderViewModel,
    modifier: Modifier
    //onNavigateToAddReminder: () -> Unit
){
    // any initialising here
    val allRemindersList = viewModel.allReminders.collectAsStateWithLifecycle(listOf())

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Your Reminders ₍^..^₎⟆")
                }
            )
        },

        floatingActionButton = {
            // ### ADD TASK BUTTON ###
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = {

                }
            ) { Icon(AppIcons.Add, contentDescription = "Add_Reminder")}

            /*
            // ### DELETE TASK BUTTON ###
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = {

                }
            ) { Icon(AppIcons.Delete, contentDescription = "Delete_Reminder")}
             */
        },

        content = { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ){
                //Spacer(modifier = Modifier.height(15.dp))
                Text(
                    // ADD FUNCTION SO TEXT DISPLAYS DIFFERENTLY IF THERE'S NO REMINDERS IN DB
                    text = "View all of your reminders here...",
                    fontSize = 20.sp,
                )

                if (allRemindersList!=null){
                    LazyColumn {
                        items(allRemindersList.value){ reminder ->  //SORT THIS SHIT
                            Row (){
                                // toggle on / off - call state from db
                                var checked by remember { mutableStateOf(true) }

                                Switch(
                                    checked = checked,
                                    onCheckedChange = {
                                        checked = it
                                    }
                                )

                                Column {
                                    Text(reminder.name)
                                    Text(reminder.reminderDateString)
                                    //Text("$(reminder.alarmTimeHour) : $(reminder.alarmTimeMinutes)")
                                }

                            } // row end

                        } // items end

                    } // lazy column end


                } // if end

            } // column end

        } // content end

    ) // SCAFFOLD END

}
