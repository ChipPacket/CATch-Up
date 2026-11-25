package com.example.catchup.ui.screens

import android.os.SystemClock
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.catchup.viewModel.ReminderViewModel
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: ReminderViewModel,
    modifier: Modifier
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Your Settings ₍^..^₎⟆",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        },
        content = { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
                //horizontalAlignment = Alignment.CenterHorizontally
            ){
                //Row (
                    //horizontalArrangement = Arrangement.SpaceAround
                //){
                    Text("Delete All Reminder Data")

                    OutlinedButton(
                        onClick = {
                            showDeleteDialog = true
                        },
                    ) {
                        Text("DELETE")
                    }
                //}
            }

            if (showDeleteDialog == true) {
                BasicAlertDialog(
                    onDismissRequest = { showDeleteDialog = false },

                )
                {
                    // ##### ACTUAL DIALOG DISPLAY #####
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        // ### DIALOG BODY ###
                        Column(
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "DELETE ALL REMINDERS",
                                Modifier.align(
                                    Alignment.CenterHorizontally,
                                ),
                                fontSize = 20.sp,
                            )

                            Row(){
                                Text(
                                    text = "Pressing CONFIRM will DELETE all of the reminders you have set.",
                                    fontSize = 16.sp,
                                )
                            }
                            Row(){
                                Text(
                                    text = "This data will not be preserved.",
                                    fontSize = 16.sp,
                                )
                            }
                            Row() {
                                TextButton(onClick = { showDeleteDialog = false }) {
                                    Text("Cancel")
                                }

                                TextButton(
                                    onClick = {
                                        viewModel.deleteAllReminders()
                                        showDeleteDialog = false
                                    }
                                ) {
                                    Text("CONFIRM")
                                }

                            }
                        }
                    }
                }

            }
        }
    )
}