package com.example.catchup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.catchup.ui.theme.CATchUpTheme
import com.example.catchup.viewModel.ReminderViewModel
import com.example.catchup.ui.screens.AppIcons
import com.example.catchup.ui.screens.HomeScreen
import com.example.catchup.ui.screens.RemindersScreen
import com.example.catchup.ui.screens.SettingsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // https://developer.android.com/training/data-storage/room
        // https://developer.android.com/training/data-storage/room/referencing-data

        setContent {
            //create viewmodel
            val viewModel: ReminderViewModel = viewModel()

            // initialise current screen
            val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()

            CATchUpTheme {
                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surface,
                            tonalElevation = 8.dp
                        ) {
                            NavigationBarItem ( // HOME
                                icon = { Icon(AppIcons.Home, contentDescription = "Home")} ,
                                onClick = { viewModel.navigateTo(ReminderViewModel.Screen.Home)},
                                selected = currentScreen is ReminderViewModel.Screen.Home,
                                label = { Text("Home")}
                            )
                            NavigationBarItem ( // REMINDERS
                                icon = { Icon(AppIcons.CheckBox, contentDescription = "Reminders")} ,
                                onClick = { viewModel.navigateTo(ReminderViewModel.Screen.Reminders)},
                                selected = currentScreen is ReminderViewModel.Screen.Reminders,
                                label = { Text("Reminders")}
                            )
                            NavigationBarItem ( // SETTINGS
                                icon = { Icon(AppIcons.Settings, contentDescription = "Settings")} ,
                                onClick = { viewModel.navigateTo(ReminderViewModel.Screen.Settings)},
                                selected = currentScreen is ReminderViewModel.Screen.Settings,
                                label = { Text("Settings")}
                            )
                        }
                    }
                ) { innerPadding ->
                    Box (modifier = Modifier.padding(innerPadding)){
                        when (val screen = currentScreen){
                            is ReminderViewModel.Screen.Home -> {
                                HomeScreen(
                                    //modifier: Modifier = Modifier
                                    TODO()
                                )

                            }
                            is ReminderViewModel.Screen.Reminders ->{
                                RemindersScreen(
                                    TODO()
                                )
                            }

                            is ReminderViewModel.Screen.Settings -> {
                                SettingsScreen(
                                    TODO()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// ##### CODE DUMP #####

//CATchUpTheme {
//    BottomNavBar() // run UI
//}

/* PERMISSIONS SHIT
// Request Notification Permission for Android 13+
val permissionLauncher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestPermission()
) { _ -> }

LaunchedEffect(key1 = true) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        permissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
    }
}


 */
