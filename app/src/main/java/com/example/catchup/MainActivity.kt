package com.example.catchup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.catchup.ui.theme.CATchUpTheme
import com.example.catchup.viewModel.ReminderViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //create viewmodel
            val viewModel: ReminderViewModel = viewModel()

            // initialise current screen
            //val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()

            viewModel.addReminder(
                name = "Killing myself lowkey",
                alarmTimeHour = 15,
                alarmTimeMinutes = 25,
                reminderDateString = "22/11/2026",
                dateMillis = 100,
                alarmTimeMillis = 100,
                completed = false,
                enabled = true
            )

            viewModel.addReminder(
                name = "Killing myself again for fun",
                alarmTimeHour = 13,
                alarmTimeMinutes = 30,
                reminderDateString = "25/11/2026",
                dateMillis = 100,
                alarmTimeMillis = 100,
                completed = false,
                enabled = true
            )

            CATchUpTheme {
                BottomNavBar(viewModel) // RUN THAT UI
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
