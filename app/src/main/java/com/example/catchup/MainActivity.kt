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

            // set up notifications


            CATchUpTheme {
                BottomNavBar(viewModel) // RUN THAT UI
            }
        }
    }
}