package com.example.catchup.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.catchup.viewModel.ReminderViewModel
import java.util.Calendar
import java.util.Locale
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


@Composable
fun RemindersScreen(
    viewModel: ReminderViewModel,
    modifier: Modifier
    //onNavigateToAddReminder: () -> Unit
){
    // any initialising here
    var showDialog by remember { mutableStateOf(false) }
    val allRemindersList = viewModel.allReminders.collectAsStateWithLifecycle(listOf())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "It's time to CATch Up! ₍^..^₎⟆",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        },

        content = { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ){
                Row (
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Button(onClick = {showDialog = true}) {
                        Text("ADD REMINDER")
                    }
                    Button(onClick = {showDialog = true}) {
                        Text("DELETE REMINDER")
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Completed",
                        fontSize = 13.sp,
                    )
                    Text(
                        text = "Name",
                        fontSize = 13.sp,
                    )
                    Text(
                        text = "Time",
                        fontSize = 13.sp,
                    )
                    Text(
                        text = "Enabled",
                        fontSize = 13.sp,
                    )
                }

                if (allRemindersList!=null){
                    LazyColumn {
                        items(allRemindersList.value){ reminder ->
                            /*
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
                                    Text(reminder.time)
                                }

                            } // row end
                             */

                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 12.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ){

                                // checkbox
                                var checked1 by remember { mutableStateOf(false) }

                                Checkbox(
                                    checked = checked1,
                                    onCheckedChange = { checked1 = it }
                                )

                                //Column {
                                    Text(reminder.name)
                                    Text(reminder.time)
                                //}

                                var checked2 by remember { mutableStateOf(true) }

                                Switch(
                                    checked = checked2,
                                    onCheckedChange = {
                                        checked2 = it
                                    }
                                )


                            }


                        } // items end

                    } // lazy column end


                } // if end

            } // column end

            if (showDialog){
                Dialog(
                    onDismissRequest = {showDialog = false}
                )
                {
                    // ##### INITIALISED VALUES #####
                    val currentTime = Calendar.getInstance()

                    val timePickerState = rememberTimePickerState(
                        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
                        initialMinute = currentTime.get(Calendar.MINUTE),
                        is24Hour = true,
                    )

                    var reminderName by remember {mutableStateOf("")}
                    var reminderDate by remember {mutableStateOf("")}

                    //val state = rememberDatePickerState(
                    //    initialSelectedDateMillis = System.currentTimeMillis()
                    //)
                    // ##### INITIALISED VALUES #####

                    // ##### ACTUAL DIALOG DISPLAY #####
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(600.dp)
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        // ### DIALOG BODY ###
                        Column(){
                            Text(
                                text="Add New Reminder ₍^..^₎⟆"
                            )

                            TimeInput(
                                state = timePickerState,
                            )




                            // date input
                            //DatePicker(state = state)
                            //Text("Selected: ${state.selectedDateMillis}")


                            // text field for title
                            TextField(
                                value = reminderName,
                                onValueChange = { reminderName = it },
                                label = { Text("Enter Name") }
                            )

                            Row(){
                                TextButton(onClick = {showDialog=false}){
                                    Text("Cancel")
                                }

                                TextButton(
                                    onClick = {

                                        val calendar = Calendar.getInstance().apply {
                                            set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                                            set(Calendar.MINUTE, timePickerState.minute)
                                            set(Calendar.SECOND, 0)
                                            set(Calendar.MILLISECOND, 0)

                                            if (timeInMillis <= System.currentTimeMillis()) {
                                                add(Calendar.DAY_OF_YEAR, 1)
                                            }
                                        }

                                        val millisTime = calendar.timeInMillis

                                        val stringTime = String.format(
                                            Locale.getDefault(),
                                            "%02d:%02d",
                                            timePickerState.hour,
                                            timePickerState.minute
                                        )

                                        viewModel.addReminder(
                                            reminderName,
                                            stringTime,
                                            millisTime,
                                            false,
                                            true
                                        )

                                        showDialog = false
                                    },
                                ){
                                    Text("Add Reminder")
                                }
                            }
                        }
                        // ### DIALOG BODY ###




                    }
                    // ##### ACTUAL DIALOG DISPLAY #####
                }
            }

        } // content end

    ) // SCAFFOLD END

}


