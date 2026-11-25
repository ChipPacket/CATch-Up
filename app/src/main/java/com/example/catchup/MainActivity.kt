package com.example.catchup

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.catchup.ui.theme.CATchUpTheme
import com.example.catchup.viewModel.ReminderViewModel
import com.example.catchup.notifications.NotificationAlarmScheduler
import com.example.catchup.viewModel.ReminderViewModelFactory
import androidx.activity.viewModels
import android.app.AlarmManager
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationManagerCompat

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Log.e(
            "ALARM_TEST",
            "Notifications enabled = ${
                NotificationManagerCompat
                    .from(this)
                    .areNotificationsEnabled()
            }"
        )

        requestPermissions(
            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            100
        )
        requestExactAlarmPermission()

        setContent {
            //create notification scheduler
            /*
            val notificationAlarmScheduler by lazy {
                NotificationAlarmScheduler(this)
            }

             */

            //create viewmodel
            //val viewModel: ReminderViewModel = viewModel()

            val viewModel: ReminderViewModel by viewModels {
                ReminderViewModelFactory(
                    application,
                    applicationContext
                )
            }

            //val context = Context
            //val viewModelFactory = MyAndroidViewModelFactory(application, context)
            //viewModel = ViewModelProvider(this, viewModelFactory)[ReminderViewModel::class.java]


            CATchUpTheme {
                BottomNavBar(viewModel) // RUN THAT UI
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun requestExactAlarmPermission() {
        val alarmManager =
            getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (!alarmManager.canScheduleExactAlarms()) {
            val intent = Intent(
                Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
            )
            startActivity(intent)
        }
    }
}