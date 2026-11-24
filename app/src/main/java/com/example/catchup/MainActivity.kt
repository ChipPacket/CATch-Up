package com.example.catchup

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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
}